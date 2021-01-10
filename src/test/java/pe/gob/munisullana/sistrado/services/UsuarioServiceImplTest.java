package pe.gob.munisullana.sistrado.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import pe.gob.munisullana.sistrado.entities.OficinaUnidad;
import pe.gob.munisullana.sistrado.entities.Rol;
import pe.gob.munisullana.sistrado.entities.Usuario;
import pe.gob.munisullana.sistrado.repositories.UsuarioRepository;

import java.util.Date;


@SpringBootTest
public class UsuarioServiceImplTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    UsuarioService usuarioService;



    @Test
    @Sql("classpath:data.sql")
    void registrarUsuario() {

        usuarioService = new UsuarioServiceImpl(usuarioRepository);

        Usuario usuario = new Usuario();
        usuario.setNombre("Secretaria");
        usuario.setApellidos("Uno");
        usuario.setEmail("secretaria_1@mail.com");
        usuario.setClave("12345678");
        usuario.setFechaCreacion(new Date());

        OficinaUnidad oficinaUnidad = new OficinaUnidad();
        oficinaUnidad.setId(1);

        Rol rol = new Rol();
        rol.setId(1);

        usuario.setOficina(oficinaUnidad);
        usuario.setRol(rol);

        usuarioService.registrarUsuario(usuario);

    }
}