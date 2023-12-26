package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductData {
    private final List<Product> productList = new ArrayList<>();

    {
        var product1 = Product.builder().id(020L).name("Vans Knu Skool black").price(660).build();
        var product2 = Product.builder().id(021L).name("iPhone 15 Pro").price(7980).build();
        var product3 = Product.builder().id(022L).name("Lakai Cambridge Black reflective").price(343).build();
        var product4 = Product.builder().id(023L).name("Nike SB Blue").price(499).build();
        var product5 = Product.builder().id(024L).name("Dell G15 RTX 3060").price(5890).build();
        var product6 = Product.builder().id(025L).name("Black baggy jeans ").price(89).build();
        productList.addAll(List.of(product1, product2, product3, product4, product5, product6));
    }

    public List<Product> getProductList() {
        return productList;
    }
}
