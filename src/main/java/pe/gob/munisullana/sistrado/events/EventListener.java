package pe.gob.munisullana.sistrado.events;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pe.gob.munisullana.sistrado.commands.SendActivationMailCommand;

@Component
public class EventListener {

    public static final Logger log = LoggerFactory.getLogger(EventListener.class);

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private SendActivationMailCommand sendActivationMailCommand;

    @Subscribe
    public void onEvent(Event event) {
        log.info("New event incoming");
        if (event instanceof UserAppCreatedEvent) {
            handleEvent((UserAppCreatedEvent) event);
        }
    }

    private void handleEvent(UserAppCreatedEvent event) {
        log.info("event: " + event.getClass().getSimpleName());
        sendActivationMailCommand.execute(event.getCiudadano());
    }

}
