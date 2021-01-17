package pe.gob.munisullana.sistrado.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.entities.VerificacionToken;
import pe.gob.munisullana.sistrado.repositories.VerificacionTokenRepository;
import pe.gob.munisullana.sistrado.commands.SendActivationMailCommand;
import pe.gob.munisullana.sistrado.utils.MailBody;
import pe.gob.munisullana.sistrado.utils.MailService;

import java.util.UUID;

@Service
public class SendActivationMailCommandImpl implements SendActivationMailCommand {

    private static final int EXPIRATION_TIME_IN_MINUTES = 4*60;

    private VerificacionTokenRepository verificacionTokenRepository;
    private MailService mailService;

    @Autowired
    public SendActivationMailCommandImpl(VerificacionTokenRepository verificacionTokenRepository, MailService mailService) {
        this.verificacionTokenRepository = verificacionTokenRepository;
        this.mailService = mailService;
    }

    @Override
    public void execute(Ciudadano ciudadano) {
        String rawToken = UUID.randomUUID().toString();
        VerificacionToken verificacionToken = new VerificacionToken(rawToken, ciudadano, EXPIRATION_TIME_IN_MINUTES);
        verificacionTokenRepository.save(verificacionToken);
        MailBody mailBody = new MailBody();
        mailBody.setEmail(ciudadano.getEmail());
        mailBody.setSubject("Activación de cuenta");
        mailBody.setContent("Ingrese al siguiente link para activar su cuenta: " + verificacionToken.getToken());
        mailService.send(mailBody);
    }
}
