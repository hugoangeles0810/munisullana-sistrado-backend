package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;
import pe.gob.munisullana.sistrado.entities.*;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.*;
import pe.gob.munisullana.sistrado.services.SolicitudService;
import pe.gob.munisullana.sistrado.utils.TimeProvider;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService  {

    private CiudadanoRepository ciudadanoRepository;
    private SolicitudRepository solicitudRepository;
    private SolicitudAdjuntoRepository solicitudAdjuntoRepository;
    private TramiteRepository tramiteRepository;
    private RequisitoRepository requisitoRepository;
    private TimeProvider timeProvider;

    @Autowired
    public SolicitudServiceImpl(CiudadanoRepository ciudadanoRepository,
                                SolicitudRepository solicitudRepository,
                                SolicitudAdjuntoRepository solicitudAdjuntoRepository,
                                TramiteRepository tramiteRepository,
                                RequisitoRepository requisitoRepository,
                                TimeProvider timeProvider) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.solicitudRepository = solicitudRepository;
        this.solicitudAdjuntoRepository = solicitudAdjuntoRepository;
        this.tramiteRepository = tramiteRepository;
        this.requisitoRepository = requisitoRepository;
        this.timeProvider = timeProvider;
    }

    @Transactional
    @Override
    public CrearSolicitudResponse save(CrearSolicitudRequest request) {
        Ciudadano ciudadano = ciudadanoRepository.findByEmail(getUserLogged().getPrincipal().toString());

        Optional<Tramite> tramite = tramiteRepository.findById(request.getTramiteId());
        if (!tramite.isPresent()) {
            throw new DomainException("El trámite seleccionado no está disponible");
        }

        List<Requisito> requisitos = requisitoRepository.findByTramiteId(tramite.get().getId());
        if (!hasCompleteWholeRequirements(request.getRequisitos(), requisitos)) {
            throw new DomainException("Debe completar todos los requisitos");
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setEstado(Solicitud.Estado.RECIBIDO);
        solicitud.setCiudadano(ciudadano);
        solicitud.setFechaCreacion(timeProvider.now());
        solicitud.setNumero(generateRequestNumber());
        solicitud.setTramite(tramite.get());

        solicitudRepository.save(solicitud);

        List<SolicitudAdjunto> solicitudAdjuntos = requisitos.stream().map(
                requisito -> new SolicitudAdjunto(
                        solicitud,
                        requisito,
                        request.getRequisitos()
                                .stream()
                                .filter(requisitoItem -> requisitoItem.getRequisitoId().equals(requisito.getId()))
                                .findFirst().get().getAdjunto(), timeProvider.now())
        ).collect(Collectors.toList());

        solicitudAdjuntoRepository.saveAll(solicitudAdjuntos);


        return new CrearSolicitudResponse(solicitud.getNumero(), solicitud.getEstado());
    }

    @Override
    public List<ProcedureItemResponse> getMyProcedures() {
        List<ProcedureItemResponse> procedureItemsResponse = solicitudRepository.findAllByCiudadano_EmailOrderByIdDesc(getUserLogged().getPrincipal().toString()).stream()
                .map(solicitud -> new ProcedureItemResponse(
                        solicitud.getId(),
                        solicitud.getNumero(),
                        solicitud.getTramite().getNombre(),
                        solicitud.getEstado().toString(),
                        "",
                        null
                )).collect(Collectors.toList());

        return procedureItemsResponse;
    }

    private boolean hasCompleteWholeRequirements(List<CrearSolicitudRequest.RequisitoItem> requestRequisitos, List<Requisito> requisitos) {

        return requestRequisitos
                .stream()
                .map(CrearSolicitudRequest.RequisitoItem::getRequisitoId)
                .collect(Collectors.toList())
                .containsAll(
                        requisitos
                                .stream()
                                .map(Requisito::getId)
                                .collect(Collectors.toList())
                );
    }

    protected Authentication getUserLogged() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String generateRequestNumber() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYA0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((10000 + rnd.nextInt(90000)) + "-");
        for (int i = 0; i < 5; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);

        return sb.toString();
    }
}
