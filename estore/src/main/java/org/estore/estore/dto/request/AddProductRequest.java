package org.estore.estore.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


public class AddProductRequest {
    private String name;
    private BigDecimal price;
    private long quantity;
    private List<MultipartFile> media;
    private String description;
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public List<MultipartFile> getMedia() {
        return media;
    }

    public void setMedia(List<MultipartFile> media) {
        this.media = media;
    }
}
