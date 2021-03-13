package pe.gob.munisullana.sistrado.controllers.webapp;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CrearSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.SubsanarSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.UploadAdjuntoResponse;
import pe.gob.munisullana.sistrado.services.SolicitudService;
import pe.gob.munisullana.sistrado.services.StorageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("webapp/api/v1/solicitud")
public class CiudadanoSolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrar Solicitud de trámite")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Object> registrarSolicitud(@Valid @RequestBody CrearSolicitudRequest crearSolicitudRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.save(crearSolicitudRequest));
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Subsanar solicitud de trámite")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Object> subsanarSolicitud(@Valid @RequestBody SubsanarSolicitudRequest subsanarSolicitudRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(solicitudService.update(subsanarSolicitudRequest));
    }

    @PostMapping(value = "/adjunto/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Subir archivo adjunto")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<UploadAdjuntoResponse> uploadAdjunto(@RequestParam("file") MultipartFile file) {
        UploadAdjuntoResponse uploadAdjuntoResponse = storageService.save(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadAdjuntoResponse);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener mis trámites")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<ProcedureItemResponse>> getMyProcedures() {
        return ResponseEntity.status(HttpStatus.OK).body(solicitudService.getLoggedCiudadanoProcedures());
    }
}
