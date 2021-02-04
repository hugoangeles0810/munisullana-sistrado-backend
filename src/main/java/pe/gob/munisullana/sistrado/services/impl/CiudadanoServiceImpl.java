package pe.gob.munisullana.sistrado.services.impl;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CreateAccountRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginResponse;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.entities.UserType;
import pe.gob.munisullana.sistrado.entities.VerificacionToken;
import pe.gob.munisullana.sistrado.events.UserAppCreatedEvent;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.CiudadanoRepository;
import pe.gob.munisullana.sistrado.repositories.VerificacionTokenRepository;
import pe.gob.munisullana.sistrado.services.CiudadanoService;
import pe.gob.munisullana.sistrado.utils.JwtTokenUtil;
import pe.gob.munisullana.sistrado.utils.MailBody;
import pe.gob.munisullana.sistrado.utils.MailService;

import java.util.Date;
import java.util.UUID;

@Service
public class CiudadanoServiceImpl implements CiudadanoService {

    private static final int EXPIRATION_TIME_IN_MINUTES = 4*60;

    private final CiudadanoRepository ciudadanoRepository;

    private final PasswordEncoder passwordEncoder;

    private final EventBus eventBus;

    private final JwtTokenUtil jwtTokenUtil;

    private final MailService mailService;

    private final VerificacionTokenRepository verificacionTokenRepository;


    @Value("${sistrado.app.url}")
    private String sistradoAppUrl;

    @Autowired
    public CiudadanoServiceImpl(CiudadanoRepository ciudadanoRepository,
                                VerificacionTokenRepository verificacionTokenRepository,
                                PasswordEncoder passwordEncoder,
                                @Lazy EventBus eventBus,
                                JwtTokenUtil jwtTokenUtil,
                                MailService mailService) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.verificacionTokenRepository = verificacionTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventBus = eventBus;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mailService = mailService;
    }

    @Override
    public void save(CreateAccountRequest createAccountRequest) {

        if (ciudadanoRepository.findByEmail(createAccountRequest.getEmail()) != null) {
            throw new DomainException("Ya existe una cuenta registrada con este correo");
        }

        if (ciudadanoRepository.findByNumeroDocumento(createAccountRequest.getDni()) != null) {
            throw new DomainException("Ya existe una cuenta registrada con este DNI");
        }

        Ciudadano newCiudadano = new Ciudadano();
        newCiudadano.setNombre(createAccountRequest.getNombres());
        newCiudadano.setApePaterno(createAccountRequest.getApePaterno());
        newCiudadano.setApeMaterno(createAccountRequest.getApeMaterno());
        newCiudadano.setEmail(createAccountRequest.getEmail());
        newCiudadano.setClave(passwordEncoder.encode(createAccountRequest.getClave()));
        newCiudadano.setNumeroDocumento(createAccountRequest.getDni());
        newCiudadano.setTelefono(createAccountRequest.getCelular());
        newCiudadano.setFechaCreacion(new Date());
        newCiudadano.setRepresentante(createAccountRequest.isRepresentante());
        newCiudadano.setRuc(createAccountRequest.getRuc());
        newCiudadano.setRazonSocial(createAccountRequest.getRazonSocial());
        newCiudadano.setDirFiscal(createAccountRequest.getDirFiscal());
        newCiudadano.setEstado(Ciudadano.Estado.PENDIENTE);

        ciudadanoRepository.save(newCiudadano);

        eventBus.post(new UserAppCreatedEvent(newCiudadano));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Ciudadano ciudadano = ciudadanoRepository.findByEmail(loginRequest.getEmail());

        if (ciudadano == null) {
            throw new DomainException("Usuario y/o contraseña incorrectos");
        }

        if (ciudadano.getEstado().equals(Ciudadano.Estado.PENDIENTE)) {
            throw new DomainException("Su cuenta está pendiente de activación");
        }

        if (ciudadano.getEstado().equals(Ciudadano.Estado.BLOQUEADO)) {
            throw new DomainException("Su cuenta está bloqueada, comuníquese con el administrador");
        }


        if (!passwordEncoder.matches(loginRequest.getPassword(), ciudadano.getClave())) {
            throw new DomainException("Usuario y/o contraseña incorrectos");
        }

        final User user = new User(ciudadano.getEmail(), ciudadano.getClave(), AuthorityUtils
                .commaSeparatedStringToAuthorityList(UserType.USER_APP.toString()));

        final String token = jwtTokenUtil.generateToken(user, UserType.USER_APP);

        LoginResponse loginResponse = new LoginResponse(
                ciudadano.getEmail(),
                ciudadano.getNumeroDocumento(),
                ciudadano.getNombre(),
                ciudadano.getApePaterno(),
                ciudadano.getApeMaterno(),
                ciudadano.getTelefono(),
                ciudadano.isRepresentante(),
                ciudadano.getRuc(),
                ciudadano.getRazonSocial(),
                ciudadano.getDirFiscal(),
                token
        );

        return loginResponse;
    }

    @Override
    public void sendActivationMail(Ciudadano ciudadano) {
        String rawToken = UUID.randomUUID().toString();
        VerificacionToken verificacionToken = new VerificacionToken(rawToken, ciudadano, EXPIRATION_TIME_IN_MINUTES);
        verificacionTokenRepository.save(verificacionToken);

        String url = sistradoAppUrl + "/verificar/" + verificacionToken.getToken();
        MailBody mailBody = new MailBody();
        mailBody.setEmail(ciudadano.getEmail());
        mailBody.setSubject("Activación de cuenta");
        mailBody.setContent("Ingrese al siguiente link para activar su cuenta: <a href=\"" + url + "\">" + url + "</a>");
        mailService.send(mailBody);
    }

    @Override
    public void verifyAccount(String token) {
        VerificacionToken verificacionToken = verificacionTokenRepository.findByToken(token);

        if (verificacionToken == null) {
            throw new DomainException("Token de activación inválido");
        }

        if (verificacionToken.getFechaExpiracion().before(new Date())) {
            throw new DomainException("Token de activación expirado");
        }

        Ciudadano ciudadano = verificacionToken.getCiudadano();
        if (ciudadano.getEstado().equals(Ciudadano.Estado.PENDIENTE)) {
            ciudadano.setEstado(Ciudadano.Estado.ACTIVO);
            ciudadano.setFechaModificacion(new Date());
            ciudadanoRepository.save(ciudadano);
        }
    }
}
