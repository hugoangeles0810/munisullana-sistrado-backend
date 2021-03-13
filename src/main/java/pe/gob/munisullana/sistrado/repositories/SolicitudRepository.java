package pe.gob.munisullana.sistrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.gob.munisullana.sistrado.entities.Solicitud;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    List<Solicitud> findAllByCiudadano_EmailOrderByIdDesc(String email);

    @Query("SELECT s FROM Solicitud s JOIN s.tramite t JOIN t.oficina o WHERE o.id = :id AND s.estado = :estado")
    List<Solicitud> findAllByTramite_Oficina_IdOrderByIdDesc(int id, Solicitud.Estado estado);

    int countByEstado(Solicitud.Estado estado);
}
