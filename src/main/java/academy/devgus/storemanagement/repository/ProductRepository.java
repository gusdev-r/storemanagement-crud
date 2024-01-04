package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductRepository {
    private final ProductData productData;

    //these methods will be used at the service to work with db of products, changes or something like that

    public List<Product> findAll () {
        return productData.getProductList();
    }
    public void delete (Product product) {
        productData.getProductList().remove(product);
    }
    public Product save (Product product) {
        productData.getProductList().add(product);
        return product;
    }
    public void update(Product product) {
        delete(product);
        save(product);
    }
    public List<Product> showAllProducts () {
        return productData.getProductList();
    }
    public List<Product> findByName (String name) {
        return productData.getProductList().stream().filter(product -> product.getName().equalsIgnoreCase(name)).toList();
    }
    public Optional<Product> findById (Long id) {
        return productData.getProductList().stream().filter(product -> product.getId().equals(id)).findFirst();
    }


}
