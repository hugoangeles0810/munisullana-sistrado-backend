package pe.gob.munisullana.sistrado.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.controllers.common.dto.ProcedureDetailResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;
import pe.gob.munisullana.sistrado.entities.*;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.*;
import pe.gob.munisullana.sistrado.services.SolicitudService;
import pe.gob.munisullana.sistrado.utils.TextFormat;
import pe.gob.munisullana.sistrado.utils.TimeProvider;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService  {

    private CiudadanoRepository ciudadanoRepository;
    private UsuarioRepository usuarioRepository;
    private SolicitudRepository solicitudRepository;
    private SolicitudAdjuntoRepository solicitudAdjuntoRepository;
    private TramiteRepository tramiteRepository;
    private RequisitoRepository requisitoRepository;
    private TimeProvider timeProvider;
    private TextFormat textFormat;

    @Autowired
    public SolicitudServiceImpl(CiudadanoRepository ciudadanoRepository,
                                UsuarioRepository usuarioRepository,
                                SolicitudRepository solicitudRepository,
                                SolicitudAdjuntoRepository solicitudAdjuntoRepository,
                                TramiteRepository tramiteRepository,
                                RequisitoRepository requisitoRepository,
                                TimeProvider timeProvider,
                                TextFormat textFormat) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.usuarioRepository = usuarioRepository;
        this.solicitudRepository = solicitudRepository;
        this.solicitudAdjuntoRepository = solicitudAdjuntoRepository;
        this.tramiteRepository = tramiteRepository;
        this.requisitoRepository = requisitoRepository;
        this.timeProvider = timeProvider;
        this.textFormat = textFormat;
    }

    private ProcedureItemResponse mapToProcedureItemResponse(Solicitud solicitud) {
        return new ProcedureItemResponse(
                solicitud.getId(),
                solicitud.getNumero(),
                solicitud.getCiudadano().getNombreCompleto(),
                solicitud.getTramite().getNombre(),
                "TUPA",
                solicitud.getEstado().toString(),
                textFormat.formatProcedureDate(solicitud.getFechaCreacion()),
                textFormat.formatProcedureDate(solicitud.getFechaModificacion())
        );
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
    public List<ProcedureItemResponse> getLoggedCiudadanoProcedures() {
        List<ProcedureItemResponse> procedureItemsResponse = solicitudRepository.findAllByCiudadano_EmailOrderByIdDesc(getUserLogged().getPrincipal().toString()).stream()
                .map(this::mapToProcedureItemResponse).collect(Collectors.toList());

        return procedureItemsResponse;
    }

    @Override
    public List<ProcedureItemResponse> getLoggedBackofficeProcedures() {
        Usuario usuario = usuarioRepository.findByEmail(getUserLogged().getPrincipal().toString());
        List<ProcedureItemResponse> procedureItemsResponse = solicitudRepository.findAllByTramite_Oficina_IdOrderByIdDesc(usuario.getOficina().getId()).stream()
                .map(this::mapToProcedureItemResponse).collect(Collectors.toList());
        return procedureItemsResponse;
    }

    @Override
    public ProcedureDetailResponse getProcedureDetail(Integer id) {
        Optional<Solicitud> holder = solicitudRepository.findById(id);

        if (!holder.isPresent()) return null;

        Solicitud solicitud = holder.get();
        List<SolicitudAdjunto> adjuntos = solicitudAdjuntoRepository.findAllBySolicitud_IdOrderByRequisito_IdDesc(id);

        return new ProcedureDetailResponse(
                id,
                solicitud.getNumero(),
                solicitud.getCiudadano().getNombreCompleto(),
                solicitud.getTramite().getNombre(),
                "Tupa",
                solicitud.getEstado().toString(),
                textFormat.formatProcedureDate(solicitud.getFechaCreacion()),
                textFormat.formatProcedureDate(solicitud.getFechaModificacion()),
                adjuntos.stream().map(adjunto -> new ProcedureDetailResponse.RequisitoAdjuntoItemResponse(
                        adjunto.getId(),
                        adjunto.getRequisito().getId(),
                        adjunto.getAdjunto(),
                        textFormat.formatProcedureDate(adjunto.getFechaCarga()),
                        adjunto.getRequisito().getNombre(),
                        adjunto.getRequisito().getDescripcion(),
                        adjunto.getRequisito().getIndicaciones()
                )).collect(Collectors.toList())
        );
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
