package org.example.springplusteam.controller;

import org.example.springplusteam.domain.product.Product;
import org.example.springplusteam.dto.product.req.ProductCreateReqDto;
import org.example.springplusteam.dto.product.resp.ProductCreateRespDto;
import org.example.springplusteam.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	@PostMapping()
	public ResponseEntity<ProductCreateRespDto> createProduct(@RequestBody ProductCreateReqDto reqDto) {
		ProductCreateRespDto createdProduct = productService.createProduct(reqDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductCreateRespDto> findById(
			@PathVariable Long productId) {
		ProductCreateRespDto respDto = productService.getProduct(productId);
		return new ResponseEntity<>(respDto, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Page<ProductCreateRespDto>> listProducts(
		@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<ProductCreateRespDto> productPage = productService.listProducts(pageable);
		return ResponseEntity.ok(productPage);
	}
}
