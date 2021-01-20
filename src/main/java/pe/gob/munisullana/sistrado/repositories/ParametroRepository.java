package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

}
