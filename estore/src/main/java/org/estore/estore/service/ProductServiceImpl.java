package org.estore.estore.service;

import lombok.AllArgsConstructor;
import org.estore.estore.dto.request.AddProductRequest;
import org.estore.estore.dto.response.AddProductResponse;
import org.estore.estore.exception.ProductNotFoundException;
import org.estore.estore.integration.CloudService;
import org.estore.estore.model.Product;
import org.estore.estore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CloudService cloudService;

    @Override
    public AddProductResponse add(AddProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        productRequest.getMedia().forEach(media -> uploadMedia(media, product));
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, AddProductResponse.class);
    }

    private void uploadMedia(MultipartFile media, Product product) {
        String blobId = cloudService.upload(media);
        String url = "localhost:8080/api/v1/media/" + blobId;
        product.getMedia().add(blobId);
    }

    @Override
    public Product getProductBy(String id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("product not found"));
    }
}
