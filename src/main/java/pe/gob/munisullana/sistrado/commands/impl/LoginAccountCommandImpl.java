package pe.gob.munisullana.sistrado.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.commands.LoginAccountCommand;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginResponse;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.entities.UserType;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.CiudadanoRepository;
import pe.gob.munisullana.sistrado.utils.JwtTokenUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LoginAccountCommandImpl implements LoginAccountCommand {

    private JwtTokenUtil jwtTokenUtil;
    private CiudadanoRepository ciudadanoRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginAccountCommandImpl(JwtTokenUtil jwtTokenUtil, CiudadanoRepository ciudadanoRepository, PasswordEncoder passwordEncoder) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.ciudadanoRepository = ciudadanoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
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

        final User user = new User(ciudadano.getEmail(), ciudadano.getClave(), getAuthorities(Arrays.asList(UserType.USER_APP.toString())));

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

    private List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
