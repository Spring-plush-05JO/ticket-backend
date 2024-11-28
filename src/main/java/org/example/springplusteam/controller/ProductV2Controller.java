package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.product.resp.ProductRespDto;
import org.example.springplusteam.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductV2Controller {

    private final ProductService productService;

    @GetMapping("/api/v2/products/{productId}")
    public ResponseEntity<ProductRespDto> getProduct(@PathVariable Long productId, @AuthenticationPrincipal AuthUser authUser) {
        ProductRespDto productRespDto = productService.getProductWithViewCount(productId, authUser.getId());
        return ResponseEntity.ok(productRespDto);
    }

}
