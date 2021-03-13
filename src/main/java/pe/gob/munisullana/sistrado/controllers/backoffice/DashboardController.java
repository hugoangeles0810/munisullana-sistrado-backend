package pe.gob.munisullana.sistrado.controllers.backoffice;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.GetMetricsResponse;
import pe.gob.munisullana.sistrado.services.SolicitudService;

@RestController
@RequestMapping(value = "backoffice/api/v1/dashboard")
@Api(value = "Dashboard - backoffice")
public class DashboardController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping(value = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Métricas de trámites")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<GetMetricsResponse> getMetrics() {
        return ResponseEntity.status(HttpStatus.OK).body(solicitudService.getMetrics());
    }
}
