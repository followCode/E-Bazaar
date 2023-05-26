package com.ecommerce.productservice.Model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "product")
public class Product {

	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
	
	
}
