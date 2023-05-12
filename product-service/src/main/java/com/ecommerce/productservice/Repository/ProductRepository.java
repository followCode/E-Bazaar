package com.ecommerce.productservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.productservice.Model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
