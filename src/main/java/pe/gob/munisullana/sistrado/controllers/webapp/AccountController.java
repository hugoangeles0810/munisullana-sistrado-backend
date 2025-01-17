package pe.gob.munisullana.sistrado.controllers.webapp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.munisullana.sistrado.commands.LoginAccountCommand;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.CreateAccountRequest;
import pe.gob.munisullana.sistrado.commands.CreateAccountCommand;
import pe.gob.munisullana.sistrado.commands.VerifyAccountCommand;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginRequest;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.LoginResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("webapp/api/v1/ciudadano")
@Api(value = "Ciudano")
public class AccountController {

    @Autowired
    private CreateAccountCommand createAccountCommand;

    @Autowired
    private VerifyAccountCommand verifyAccountCommand;

    @Autowired
    private LoginAccountCommand loginAccountCommand;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Iniciar sesión de ciudadano")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginAccountCommand.execute(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

    @PostMapping(value = "/registro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrar ciudadano")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Object> createAccount(@Valid  @RequestBody CreateAccountRequest createAccountRequest) {
        createAccountCommand.execute(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/verificar/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Verificar cuenta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity verifyAccount(@PathVariable("token") String token) {
        verifyAccountCommand.execute(token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
