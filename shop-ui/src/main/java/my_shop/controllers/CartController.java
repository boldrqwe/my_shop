package my_shop.controllers;

import my_shop.service.CartService;
import my_shop.service.model.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import my_shop.service.ProductService;

@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }
}
