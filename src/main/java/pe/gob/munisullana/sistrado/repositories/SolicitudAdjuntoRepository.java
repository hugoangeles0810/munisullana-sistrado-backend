package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.munisullana.sistrado.entities.SolicitudAdjunto;

import java.util.List;

public interface SolicitudAdjuntoRepository extends JpaRepository<SolicitudAdjunto, Integer> {

    List<SolicitudAdjunto> findAllBySolicitud_IdOrderByRequisito_IdDesc(int id);
}
