package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.TramiteTipo;

@Repository
public interface TramiteTipoRepository extends JpaRepository<TramiteTipo, Integer> {

}
