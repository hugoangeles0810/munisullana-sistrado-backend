package pe.gob.munisullana.sistrado.services;

import org.springframework.web.multipart.MultipartFile;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.UploadAdjuntoResponse;

public interface StorageService {

    UploadAdjuntoResponse save(MultipartFile file);
}
