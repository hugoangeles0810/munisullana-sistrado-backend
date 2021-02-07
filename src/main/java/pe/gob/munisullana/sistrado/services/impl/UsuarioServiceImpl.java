package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginResponse;
import pe.gob.munisullana.sistrado.entities.UserType;
import pe.gob.munisullana.sistrado.entities.Usuario;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.UsuarioRepository;
import pe.gob.munisullana.sistrado.services.UsuarioService;
import pe.gob.munisullana.sistrado.utils.JwtTokenUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            throw new DomainException("Usuario y/o contraseña incorrectos");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getClave())) {
            throw new DomainException("Usuario y/o contraseña incorrectos");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getClave())) {
            throw new DomainException("Usuario y/o contraseña incorrectos");
        }

        final User user = new User(usuario.getEmail(), usuario.getClave(), AuthorityUtils
                .commaSeparatedStringToAuthorityList(UserType.USER_BACKOFFICE.toString()));

        final String token = jwtTokenUtil.generateToken(user, UserType.USER_BACKOFFICE);

        return new LoginResponse(
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApePaterno(),
                usuario.getApeMaterno(),
                usuario.getOficina().getNombre(),
                token
        );
    }
}
