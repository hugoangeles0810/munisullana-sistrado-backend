package pe.gob.munisullana.sistrado.services;

import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.munisullana.sistrado.entities.Usuario;
import pe.gob.munisullana.sistrado.repositories.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
