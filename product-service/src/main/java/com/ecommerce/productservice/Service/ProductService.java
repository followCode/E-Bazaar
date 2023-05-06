package com.ecommerce.productservice.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.productservice.Dto.ProductRequest;
import com.ecommerce.productservice.Dto.ProductResponse;
import com.ecommerce.productservice.Model.Product;
import com.ecommerce.productservice.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
						.name(productRequest.getName())
						.description(productRequest.getDescription())
						.price(productRequest.getPrice())
						.build();
		
		productRepository.save(product);
		log.info("product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts() {
		
		List<Product> products = productRepository.findAll();
		
		List<ProductResponse> res = products.stream().map((product) -> {
			ProductResponse productResponse = ProductResponse.builder()
					.id(product.getId())
					.name(product.getName())
					.description(product.getDescription())
					.price(product.getPrice())
					.build();
			
			return productResponse;
		}).toList();
		return res;
	}
	
}
