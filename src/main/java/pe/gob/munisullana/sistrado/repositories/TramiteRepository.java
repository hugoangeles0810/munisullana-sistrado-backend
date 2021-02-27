package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.Requisito;
import pe.gob.munisullana.sistrado.entities.Tramite;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Integer> {

}
