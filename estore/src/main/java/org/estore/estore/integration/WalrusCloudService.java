package org.estore.estore.integration;

import org.estore.estore.dto.response.walrus.WalrusUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.PUT;

@Component
public class WalrusCloudService implements CloudService{

//    @Autowired
//    private CloudService cloudService;

    @Override
    public String upload(MultipartFile file) {
        String walrusUrl = "https://publisher.walrus-testnet.walrus.space/v1/blobs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        Map<String, Object> params = new HashMap<>();
        params.put("epochs", 5);
        params.put("send_object_to", "0x5884e45f7d154254ec1d1fe4cf0342be771769e6bdcbd8b148e1962a0ff0a8a6");
        Resource resource = file.getResource();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity =
                new HttpEntity<>(resource, headers);
        ResponseEntity<WalrusUploadResponse> response = restTemplate.exchange(URI.create(walrusUrl), PUT, requestEntity, WalrusUploadResponse.class);
        WalrusUploadResponse walrusUploadResponse = response.getBody();
        boolean isFileAlreadyExists = walrusUploadResponse != null && walrusUploadResponse.getNewlyCreated() == null;
        if (isFileAlreadyExists) return walrusUploadResponse.getAlreadyCertified().getBlobId();
        return walrusUploadResponse.getNewlyCreated().getBlobObject().getBlobId();
    }
}
