package academy.devgus.storemanagement.service;

import academy.devgus.storemanagement.domain.Customer;
import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.repository.CPRepository;
import academy.devgus.storemanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    private final CPRepository csRepository;

    public void delete (Long id) {
        var customer = findByIdToDeleteOrUpdated(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Customer not found to be deleted"));
        customerRepository.delete(customer);
    }
    public List<Customer> findAll () {
        return customerRepository.findAll();
    }

    public void update(Long id, Customer customerToUpdate) {
        /* var customer = findByIdToDeleteOrUpdated(customerToUpdate.getIdClient()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Customer not found to be updated"));
        customerToUpdate.setCreatedAt(customer.getCreatedAt());
                //assertCustomerExists(customerToUpdate); */
       customerRepository.update(id, customerToUpdate);
    }

    //can return or not, in this case I prefer to not use optional but use the exception
    public Customer findById (Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "The customer required can't be found"));
    }
    public Optional<Customer> findByIdToDeleteOrUpdated(Long id) {
        return customerRepository.findById(id);
    }

    //another option to config the update and delete by the cpf of the client (cpf is unique element as id)
    public Customer findByCpf (Long cpf) {
        return customerRepository.findByCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "The customer required can't be found"));
    }
    public List<Customer> findByName (String name) {
        return customerRepository.findByName(name);
    }
    public List<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Customer save (Customer customer) {
        return customerRepository.save(customer);
    }
    private void assertCustomerExists(Customer customerToUpdate) {
        findById(customerToUpdate.getIdClient());
    }






    public void updateOrderFromClient(String cName, Long pID, Product uP) {
        csRepository.updateOrderFromCustomer(cName, pID, uP);
    }
    public List<Product> removeProductFromTheCustomer (String name, Product product) {
        Customer c = (Customer) findByName(name);
        if (c != null) {
            c.getOrders().remove(product);
        }
        return c.getOrders();
    }
    public List<Product> addProductFromTheCustomer (String name, Product product) {
        Customer c = (Customer) findByName(name);
        if (c != null) {
            c.getOrders().add(product);
        }
        return c.getOrders();
    }
}
