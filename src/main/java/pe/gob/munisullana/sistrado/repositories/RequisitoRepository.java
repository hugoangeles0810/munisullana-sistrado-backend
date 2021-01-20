package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.Requisito;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Integer> {

}
