package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
}
