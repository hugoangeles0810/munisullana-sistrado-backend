package pe.gob.munisullana.sistrado.commands.impl;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CreateAccountRequest;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.events.UserAppCreatedEvent;
import pe.gob.munisullana.sistrado.repositories.CiudadanoRepository;
import pe.gob.munisullana.sistrado.commands.CreateAccountCommand;

import java.util.Date;

@Service
public class CreateAccountCommandImpl implements CreateAccountCommand {

    private CiudadanoRepository ciudadanoRepository;

    private PasswordEncoder passwordEncoder;

    private EventBus eventBus;

    @Autowired
    public CreateAccountCommandImpl(CiudadanoRepository ciudadanoRepository,
                                    PasswordEncoder passwordEncoder,
                                    EventBus eventBus) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(CreateAccountRequest createAccountRequest) {
        Ciudadano newCiudadano = new Ciudadano();

        newCiudadano.setNombre(createAccountRequest.getNombres());
        newCiudadano.setApePaterno(createAccountRequest.getApePaterno());
        newCiudadano.setApeMaterno(createAccountRequest.getApeMaterno());
        newCiudadano.setEmail(createAccountRequest.getEmail());
        newCiudadano.setClave(passwordEncoder.encode(createAccountRequest.getClave()));
        newCiudadano.setNumeroDocumento(createAccountRequest.getDni());
        newCiudadano.setTelefono(createAccountRequest.getCelular());
        newCiudadano.setFechaCreacion(new Date());
        newCiudadano.setRepresentante(createAccountRequest.isRepresentante());
        newCiudadano.setRuc(createAccountRequest.getRuc());
        newCiudadano.setRazonSocial(createAccountRequest.getRazonSocial());
        newCiudadano.setDirFiscal(createAccountRequest.getDirFiscal());
        newCiudadano.setEstado(Ciudadano.Estado.PENDIENTE);

        ciudadanoRepository.save(newCiudadano);

        eventBus.post(new UserAppCreatedEvent(newCiudadano));
    }
}
