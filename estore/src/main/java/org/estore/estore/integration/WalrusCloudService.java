package org.estore.estore.integration;

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

    @Override
    public String upload(MultipartFile file) {
        String walrusUrl = "https://publisher.walrus-testnet.walrus.space/v1/blobs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultipartFile> requestEntity =
                new HttpEntity<>(file, headers);

        Map<String, Object> params = new HashMap<>();
        params.put("epochs", 5);
        prams.put("send_object_to", "0x5884e45f7d154254ec1d1fe4cf0342be771769e6bdcbd8b148e1962a0ff0a8a6");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(URI.create(walrusUrl), PUT, requestEntity, null);
        return "";
    }
}
