package pe.gob.munisullana.sistrado.commands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.munisullana.sistrado.commands.VerifyAccountCommand;
import pe.gob.munisullana.sistrado.entities.Ciudadano;
import pe.gob.munisullana.sistrado.entities.VerificacionToken;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.repositories.CiudadanoRepository;
import pe.gob.munisullana.sistrado.repositories.VerificacionTokenRepository;

import java.util.Date;

@Service
public class VerifyAccountCommandImpl implements VerifyAccountCommand {

    private CiudadanoRepository ciudadanoRepository;
    private VerificacionTokenRepository verificacionTokenRepository;

    @Autowired
    public VerifyAccountCommandImpl(CiudadanoRepository ciudadanoRepository, VerificacionTokenRepository verificacionTokenRepository) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.verificacionTokenRepository = verificacionTokenRepository;
    }

    @Override
    public void execute(String token) {
        VerificacionToken verificacionToken = verificacionTokenRepository.findByToken(token);

        if (verificacionToken == null) {
            throw new DomainException("Token de activación inválido");
        }

        if (verificacionToken.getFechaExpiracion().before(new Date())) {
            throw new DomainException("Token de activación expirado");
        }

        Ciudadano ciudadano = verificacionToken.getCiudadano();
        if (ciudadano.getEstado().equals(Ciudadano.Estado.PENDIENTE)) {
            ciudadano.setEstado(Ciudadano.Estado.ACTIVO);
            ciudadano.setFechaModificacion(new Date());
            ciudadanoRepository.save(ciudadano);
        }
    }

}
