package pe.gob.munisullana.sistrado.controllers.backoffice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.backoffice.dto.LoginResponse;
import pe.gob.munisullana.sistrado.services.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("backoffice/api/v1/usuario")
@Api(value = "Usuarios del sistema")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Iniciar sesión de usuario del sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = usuarioService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
