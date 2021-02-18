package pe.gob.munisullana.sistrado.controllers.backoffice;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.ObservarSolicitudRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.ProcedureItemResponse;
import pe.gob.munisullana.sistrado.services.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("backoffice/api/v1/solicitud")
@Api(value = "Solicitud de trámites - backoffice")
public class BackofficeSolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener trámites pendientes")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<ProcedureItemResponse>> getMyProcedures() {
        return ResponseEntity.status(HttpStatus.OK).body(solicitudService.getLoggedBackofficeProcedures());
    }

    @PutMapping(value = "/observar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Observar trámite")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity observarTramite(@RequestBody ObservarSolicitudRequest request) {
        solicitudService.observarSolicitud(request);
        return ResponseEntity.ok().build();
    }
}
