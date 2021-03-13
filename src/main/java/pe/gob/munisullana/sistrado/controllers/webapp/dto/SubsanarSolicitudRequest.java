package pe.gob.munisullana.sistrado.controllers.webapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SubsanarSolicitudRequest {

    @NotNull
    private Integer id;

    @NotEmpty
    private List<RequisitoItem> requisitos;

    public Integer getId() {
        return id;
    }

    public List<RequisitoItem> getRequisitos() {
        return requisitos;
    }

    static public class RequisitoItem {

        @NotNull
        private Integer id;

        @NotBlank
        private String adjunto;

        public Integer getId() {
            return id;
        }

        public String getAdjunto() {
            return adjunto;
        }
    }
}
