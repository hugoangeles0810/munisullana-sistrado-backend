package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.VerificacionToken;

@Repository
public interface VerificacionTokenRepository extends JpaRepository<VerificacionToken, Long> {

    VerificacionToken findByToken(String token);
}
