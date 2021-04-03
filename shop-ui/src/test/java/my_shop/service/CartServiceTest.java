package my_shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import my_shop.controllers.repr.ProductRepr;
import my_shop.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOnProduct() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 1);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(1, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }
}
