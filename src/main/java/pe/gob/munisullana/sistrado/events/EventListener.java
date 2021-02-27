package pe.gob.munisullana.sistrado.events;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.munisullana.sistrado.services.CiudadanoService;
import pe.gob.munisullana.sistrado.services.SolicitudSeguimientoService;

public class EventListener {

    public static final Logger log = LoggerFactory.getLogger(EventListener.class);

    private CiudadanoService ciudadanoService;
    private SolicitudSeguimientoService solicitudSeguimientoService;

    public EventListener(CiudadanoService ciudadanoService,
                         SolicitudSeguimientoService solicitudSeguimientoService) {
        this.ciudadanoService = ciudadanoService;
        this.solicitudSeguimientoService = solicitudSeguimientoService;
    }

    @Subscribe
    public void onEvent(Event event) {
        log.info("New event incoming");
        log.info("event: " + event.getClass().getSimpleName());
        if (event instanceof UserAppCreatedEvent) {
            handleEvent((UserAppCreatedEvent) event);
        } else if (event instanceof SolicitudUpdatedEvent) {
            handleEvent((SolicitudUpdatedEvent) event);
        } else if (event instanceof SolicitudCreatedEvent) {
            handleEvent((SolicitudCreatedEvent) event);
        }
    }

    private void handleEvent(SolicitudCreatedEvent event) {
    }

    private void handleEvent(SolicitudUpdatedEvent event) {
        this.solicitudSeguimientoService.sendMailSolicitudUpdated(event.getSolicitud());
    }

    private void handleEvent(UserAppCreatedEvent event) {
        this.ciudadanoService.sendActivationMail(event.getCiudadano());
    }

}
