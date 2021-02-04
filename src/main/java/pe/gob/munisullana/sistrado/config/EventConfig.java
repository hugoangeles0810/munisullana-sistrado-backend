package pe.gob.munisullana.sistrado.config;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.gob.munisullana.sistrado.events.EventListener;
import pe.gob.munisullana.sistrado.services.CiudadanoService;

@Configuration
public class EventConfig {

    @Autowired
    private  CiudadanoService ciudadanoService;

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener(ciudadanoService));
        return eventBus;
    }
}
