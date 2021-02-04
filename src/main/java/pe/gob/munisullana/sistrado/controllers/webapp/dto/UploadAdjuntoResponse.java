package pe.gob.munisullana.sistrado.controllers.webapp.dto;

public class UploadAdjuntoResponse {

    private String filePath;

    public UploadAdjuntoResponse(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
