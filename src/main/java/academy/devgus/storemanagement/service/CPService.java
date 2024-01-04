package academy.devgus.storemanagement.service;

import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.repository.CPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CPService {
    //remove, findByName, findByCpf, findByEmail, findById, update

    private final CPRepository cpRepository;

    public void delete (Long customerId, Long productId) {
         var product = findSpecificOrdersByCustomerId(customerId, productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product wasn't found to be deleted or this product was deleted yet."));
         cpRepository.removeProductFromClient(product.getName(), product.getId());
    }

    public void updateOrders(Long customerId, Long productId, Product updatedProduct) {
        var product = findSpecificOrdersByCustomerId(customerId, productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product wasn't found to be deleted or this product was deleted yet."));
        cpRepository.updateOrderFromCustomer(product.getName(), product.getId(), updatedProduct);
    }

    public Product saveOrders(Long idCustomer, Product product) {
        cpRepository.saveOrdersInTheListFromCustomer(idCustomer, product);
        return product;
    }
    public Optional<Product> findSpecificOrdersByCustomerId(Long idCustomer, Long idProduct) {
        return cpRepository.findOrdersByTheCustomerId(idCustomer, idProduct);
    }
    public List<Product> findAllOrdersByCustomerId(Long id) {
        return cpRepository.findAllOrdersByTheCustomerId(id);
    }
    public List<Product> findOrdersByCustomerName(String fullCustomerName) {
        return findOrdersByCustomerName(fullCustomerName);
    }
    public List<Product> findOrdersByCustomerEmail(String email) {
        return findOrdersByCustomerEmail(email);
    }
    public List<Product> findOrdersByCustomerCpf(String cpf) {
        return findOrdersByCustomerCpf(cpf);
    }
}

