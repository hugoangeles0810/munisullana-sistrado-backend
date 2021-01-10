package pe.gob.munisullana.sistrado.services;

import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.entities.Usuario;

@Service
public interface UsuarioService {

    void registrarUsuario(Usuario usuario);
}
