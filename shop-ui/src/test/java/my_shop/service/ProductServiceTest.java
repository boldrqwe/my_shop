package my_shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import my_shop.controllers.repr.ProductRepr;
import my_shop.persist.model.Brand;
import my_shop.persist.model.Category;
import my_shop.persist.model.Product;
import my_shop.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        ProductServiceImpl impl = new ProductServiceImpl();
        impl.setProductRepository(productRepository);
        productService = impl;
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name");
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
    }

}
