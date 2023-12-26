package academy.devgus.storemanagement.service;

import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public void delete (Long id) {
        var product = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product wasn't found to be deleted or this product was deleted yet."));
    }
    public void update (Product productToUpdate) {
        var product = findById(productToUpdate.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product wasn't found to be updated."));
        //changing the time of the creation for the new "product user" created after the update
        productRepository.update(product);
    }

    //can return or not
    public Optional<Product> findById (Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll () {
        return productRepository.findAll();
    }

    public List<Product> findByName (String name) {
        return productRepository.findByName(name);
    }

    public Product save (Product customer) {
        return productRepository.save(customer);
    }
}
