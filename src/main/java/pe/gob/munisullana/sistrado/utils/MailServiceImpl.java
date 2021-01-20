package pe.gob.munisullana.sistrado.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailServiceImpl implements MailService {

    public static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Override
    public void send(MailBody mailBody) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(mailBody.getEmail());
            helper.setText(mailBody.getContent(), true);
            helper.setSubject(mailBody.getSubject());
            sender.send(message);
            log.info("Mail enviado!");
        } catch (MessagingException e) {
            log.error("Hubo un error al enviar el mail: \n" + mailBody, e);
        }
    }

}
