package pe.gob.munisullana.sistrado.controllers.common;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.munisullana.sistrado.controllers.common.dto.ProcedureDetailResponse;
import pe.gob.munisullana.sistrado.services.SolicitudService;

@RestController
@RequestMapping("common/api/v1/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener detalle de trámite")
    @ApiImplicitParam(name = "Authorization", paramType = "header", type = "String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<ProcedureDetailResponse> getProdecureDetail(@PathVariable("id") Integer id) {
        ProcedureDetailResponse procedureDetail = solicitudService.getProcedureDetail(id);

        if (procedureDetail == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(procedureDetail);
    }
}
