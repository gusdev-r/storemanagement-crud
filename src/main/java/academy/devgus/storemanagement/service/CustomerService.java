package academy.devgus.storemanagement.service;

import academy.devgus.storemanagement.domain.Customer;
import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.repository.CPRepository;
import academy.devgus.storemanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CPRepository csRepository;

    public void delete (Long id) {
        var customer = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The customer wasn't found to be deleted."));
        customerRepository.delete(customer);
    }
    public List<Customer> findAll () {
        return customerRepository.findAll();
    }
    public void update (Customer customerToUpdate) {
     var customer = findById(customerToUpdate.getIdClient()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The customer wasn't found to be updated."));
     //changing the time of the creation for the new "customer user" created after the update
     customerToUpdate.setCreatedAt(customer.getCreatedAt());
     customerRepository.update(customer);
    }

    //can return or not
    public Optional<Customer> findById (Long id) {
        return customerRepository.findById(id);
    }
    public List<Product> addProductFromTheCustomer (String name, Product product) {
        Customer c = (Customer) findByName(name);
        if (c != null) {
            c.getOrders().add(product);
        }
        return c.getOrders();
    }

    public List<Product> removeProductFromTheCustomer (String name, Product product) {
        Customer c = (Customer) findByName(name);
        if (c != null) {
            c.getOrders().remove(product);
        }
        return c.getOrders();
    }
    //another option to config the update and delete by the cpf of the client (cpf is unic element as id)
    public List<Customer> findByCpf (String cpf) {
        return customerRepository.findByCpf(cpf);
    }
    public List<Customer> findByName (String name) {
        return customerRepository.findByName(name);
    }
    public Customer save (Customer customer) {
        return customerRepository.save(customer);
    }
    public void updateOrderFromClient(String cName, Long pID, Product uP) {
        csRepository.updateOrderFromCustomer(cName, pID, uP);
    }
}
