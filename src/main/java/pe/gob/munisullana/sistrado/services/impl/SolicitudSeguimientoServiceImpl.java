package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.entities.Solicitud;
import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;
import pe.gob.munisullana.sistrado.services.SolicitudSeguimientoService;
import pe.gob.munisullana.sistrado.utils.MailBody;
import pe.gob.munisullana.sistrado.utils.MailService;

@Service
public class SolicitudSeguimientoServiceImpl implements SolicitudSeguimientoService {

    @Autowired
    private MailService mailService;

    @Override
    public void sendMailSolicitudUpdated(SolicitudSeguimiento solicitudSeguimiento) {

        MailBody mailBody = new MailBody();
        Solicitud solicitud = solicitudSeguimiento.getSolicitud();
        String email = solicitud.getCiudadano().getEmail();
        mailBody.setEmail(email);
        mailBody.setSubject("El estado de su solicitud ha sido actualizado");
        mailBody.setContent("Su solicitud nro: " + solicitud.getNumero() + " ha pasado a estado: " + solicitudSeguimiento.getEstado());
        mailService.send(mailBody);
    }

    @Override
    public void sendMailSolicitudCreated(SolicitudSeguimiento solicitudSeguimiento) {
        MailBody mailBody = new MailBody();
        Solicitud solicitud = solicitudSeguimiento.getSolicitud();
        String email = solicitud.getCiudadano().getEmail();
        mailBody.setEmail(email);
        mailBody.setSubject("Hemos recibido tu solicitud");
        mailBody.setContent("Su solicitud nro: " + solicitud.getNumero() + " ha sido recibida, te mantendremos al tanto del proceso.");
        mailService.send(mailBody);
    }
}
