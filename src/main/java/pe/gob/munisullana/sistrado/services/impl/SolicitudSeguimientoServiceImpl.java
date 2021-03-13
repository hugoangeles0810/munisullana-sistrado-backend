package pe.gob.munisullana.sistrado.services.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pe.gob.munisullana.sistrado.entities.Solicitud;
import pe.gob.munisullana.sistrado.entities.SolicitudAdjunto;
import pe.gob.munisullana.sistrado.entities.SolicitudSeguimiento;
import pe.gob.munisullana.sistrado.services.SolicitudSeguimientoService;
import pe.gob.munisullana.sistrado.utils.MailBody;
import pe.gob.munisullana.sistrado.utils.MailService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SolicitudSeguimientoServiceImpl implements SolicitudSeguimientoService {

    @Autowired
    private MailService mailService;

    @Autowired
    private Configuration configuration;

    @Override
    public void sendMailSolicitudUpdated(SolicitudSeguimiento solicitudSeguimiento) {
        try {
            configuration.setClassForTemplateLoading(getClass(), "/templates");
            Template t = configuration.getTemplate("solicitud-actualizada.ftl");

            Map<String, Object> map = new HashMap<>();
            map.put("nombre", solicitudSeguimiento.getSolicitud().getCiudadano().getNombreCompleto());
            map.put("numero", solicitudSeguimiento.getSolicitud().getNumero());
            map.put("nombreTramite", solicitudSeguimiento.getSolicitud().getTramite().getNombre());
            map.put("adjuntos", solicitudSeguimiento.getSolicitud().getAdjuntos().stream().map(SolicitudAdjunto::getAdjunto).collect(Collectors.toList()));
            map.put("estado", solicitudSeguimiento.getSolicitud().getEstado().toString());
            map.put("observaciones", solicitudSeguimiento.getSolicitud().getObservaciones());
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);

            MailBody mailBody = new MailBody();
            Solicitud solicitud = solicitudSeguimiento.getSolicitud();
            String email = solicitud.getCiudadano().getEmail();
            mailBody.setEmail(email);
            mailBody.setSubject("El estado de su solicitud ha sido actualizado");
            mailBody.setContent(content);
            mailService.send(mailBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMailSolicitudCreated(SolicitudSeguimiento solicitudSeguimiento) {
        try {
            configuration.setClassForTemplateLoading(getClass(), "/templates");
            Template t = configuration.getTemplate("solicitud-recibida.ftl");

            Map<String, Object> map = new HashMap<>();
            map.put("nombre", solicitudSeguimiento.getSolicitud().getCiudadano().getNombreCompleto());
            map.put("numero", solicitudSeguimiento.getSolicitud().getNumero());
            map.put("nombreTramite", solicitudSeguimiento.getSolicitud().getTramite().getNombre());
            map.put("adjuntos", solicitudSeguimiento.getSolicitud().getAdjuntos().stream().map(SolicitudAdjunto::getAdjunto).collect(Collectors.toList()));
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);

            MailBody mailBody = new MailBody();
            Solicitud solicitud = solicitudSeguimiento.getSolicitud();
            String email = solicitud.getCiudadano().getEmail();
            mailBody.setEmail(email);
            mailBody.setSubject("Hemos recibido tu solicitud");
            mailBody.setContent(content);
            mailService.send(mailBody);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
