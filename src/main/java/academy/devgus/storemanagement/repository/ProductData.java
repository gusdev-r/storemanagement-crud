package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Getter
//@Setter
//@ToString
//@Builder
public class ProductData {
    //@Autowired
    private final List<Product> productList = new ArrayList<>();

      {
        var product1 = Product.builder().id(20L).name("Vans Knu Skool black").price(660).build();
        var product2 = Product.builder().id(21L).name("iPhone 15 Pro").price(7980).build();
        var product3 = Product.builder().id(22L).name("Lakai Cambridge Black reflective").price(343).build();
        var product4 = Product.builder().id(23L).name("Nike SB Blue").price(499).build();
        var product5 = Product.builder().id(24L).name("Dell G15 RTX 3060").price(5890).build();
        var product6 = Product.builder().id(25L).name("Black baggy jeans ").price(89).build();
        productList.addAll(List.of(product1, product2, product3, product4, product5, product6));
    }


}
