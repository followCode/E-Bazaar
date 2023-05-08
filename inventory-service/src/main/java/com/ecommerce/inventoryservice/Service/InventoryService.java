package com.ecommerce.inventoryservice.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.inventoryservice.Dto.InventoryResponse;
import com.ecommerce.inventoryservice.Model.Inventory;
import com.ecommerce.inventoryservice.Repository.InventoryRepository;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

	@Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        log.info("Checking Inventory");
        return skuCodes.stream()
                .map(skucode -> {
                	Inventory inventory = this.inventoryRepository.findBySkuCode(skucode);
                	return InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build();
                }).toList();
    }
}