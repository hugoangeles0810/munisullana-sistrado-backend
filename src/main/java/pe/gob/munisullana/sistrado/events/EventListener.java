package pe.gob.munisullana.sistrado.events;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pe.gob.munisullana.sistrado.services.CiudadanoService;

public class EventListener {

    public static final Logger log = LoggerFactory.getLogger(EventListener.class);

    private CiudadanoService ciudadanoService;

    public EventListener(CiudadanoService ciudadanoService) {
        this.ciudadanoService = ciudadanoService;
    }

    @Subscribe
    public void onEvent(Event event) {
        log.info("New event incoming");
        if (event instanceof UserAppCreatedEvent) {
            handleEvent((UserAppCreatedEvent) event);
        }
    }

    private void handleEvent(UserAppCreatedEvent event) {
        log.info("event: " + event.getClass().getSimpleName());
        ciudadanoService.sendActivationMail(event.getCiudadano());
    }

}
