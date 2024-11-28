package org.example.springplusteam.domain.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

	List<Product> findAllWithPagination(Pageable pageable);
	long countProducts();

}
