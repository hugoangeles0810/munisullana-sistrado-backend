package pe.gob.munisullana.sistrado.config;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.gob.munisullana.sistrado.events.EventListener;
import pe.gob.munisullana.sistrado.services.CiudadanoService;
import pe.gob.munisullana.sistrado.services.SolicitudSeguimientoService;

@Configuration
public class EventConfig {

    @Autowired
    private  CiudadanoService ciudadanoService;

    @Autowired
    private SolicitudSeguimientoService solicitudSeguimientoService;

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener(ciudadanoService, solicitudSeguimientoService));
        return eventBus;
    }
}
