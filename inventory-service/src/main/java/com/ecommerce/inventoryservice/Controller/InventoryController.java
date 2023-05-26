package com.ecommerce.inventoryservice.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.inventoryservice.Dto.InventoryResponse;
import com.ecommerce.inventoryservice.Service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) {
        log.info("Received inventory check request for skuCodes: {}", skuCodes);
        return inventoryService.isInStock(skuCodes);
    }
}