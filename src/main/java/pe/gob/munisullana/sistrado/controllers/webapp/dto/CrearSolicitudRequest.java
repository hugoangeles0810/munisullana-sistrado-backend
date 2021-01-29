package pe.gob.munisullana.sistrado.controllers.webapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CrearSolicitudRequest {

    @NotNull
    private Integer tramiteId;

    @NotEmpty
    private List<RequisitoItem> requisitos;

    public Integer getTramiteId() {
        return tramiteId;
    }

    public List<RequisitoItem> getRequisitos() {
        return requisitos;
    }

    public class RequisitoItem {

        @NotNull
        private Integer requisitoId;

        @NotBlank
        private String adjunto;

        public Integer getRequisitoId() {
            return requisitoId;
        }

        public String getAdjunto() {
            return adjunto;
        }
    }
}
