package pe.gob.munisullana.sistrado.controllers.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.munisullana.sistrado.commands.GetAllTramitesCommand;
import pe.gob.munisullana.sistrado.controllers.common.dto.TramiteItemResponse;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("common/api/v1/tramite")
@Api(value = "Trámite")
public class TramiteController {

    @Autowired
    private GetAllTramitesCommand getAllTramitesCommand;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar trámites")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<TramiteItemResponse>> getAllTramites() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        getAllTramitesCommand.execute().stream()
                                .map(item -> new TramiteItemResponse(
                                        item.getId(),
                                        item.getCodigo(),
                                        item.getNombre(),
                                        item.getDescripcion(),
                                        item.getIndicaciones()
                                ))
                                .collect(Collectors.toList())
                );
    }
}
