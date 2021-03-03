package pe.gob.munisullana.sistrado.services.impl;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.munisullana.sistrado.controllers.webapp.dto.UploadAdjuntoResponse;
import pe.gob.munisullana.sistrado.exceptions.DomainException;
import pe.gob.munisullana.sistrado.services.StorageService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

    private Path root;

    private static final String BUCKET = "sistrado-files";

    @PostConstruct
    public void init() {
        try {
            root = Paths.get(Files.createTempDirectory("uploads").toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public UploadAdjuntoResponse save(MultipartFile file) {
        try {
            String[] split = file.getOriginalFilename().split("\\.");
            String newName = UUID.randomUUID().toString() + "."  + split[split.length - 1];

            CloudStorageAccount storageAccount = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=sistrado;AccountKey=auXaccOwS9I7j7R224zLXpztPk5w9L/NCZc1iFpVnrX0KgteC4Nxkunp/yShoYNRDkWiJ0hAdjX66TOC9KAraw==;EndpointSuffix=core.windows.net");
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference(BUCKET);
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

            CloudBlockBlob blob = container.getBlockBlobReference(newName);
            blob.upload(file.getInputStream(), file.getSize());

            log.info("File uploaded : " + blob.getUri().toString());
            return new UploadAdjuntoResponse(blob.getUri().toString());
        } catch (Exception e) {
            log.error("Error al subir el archivo", e);
            throw new DomainException("Ocurrió un error al subir el archivo");
        }
    }
}
