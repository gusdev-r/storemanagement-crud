package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CustomerRepository {
    //@Autowired
    private final CustomerData customerData;

    public void delete (Customer customer) {
        customerData.getCustomersList().remove(customer);
    }
    public List<Customer> findAll() {
        return customerData.getCustomersList();
    }
    public Optional<Customer> findById(Long id) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getIdClient().equals(id)).findFirst();
    }
    public Optional<Customer> findByCpf(Long cpf) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getCpf().equals(cpf)).findFirst();
    }
    //It'll work as an add like the example learned
    public Customer save (Customer customer) {
        customerData.getCustomersList().add(customer);
        return customer;
    }
    public void update (Long id, Customer customer) {
        var storedCustomerOptional = findById(id);
        var storedCustomer = storedCustomerOptional.get();
        storedCustomer.setFullName(customer.getFullName());
        storedCustomer.setCpf(customer.getCpf());
        storedCustomer.setEmail(customer.getEmail());
        storedCustomer.setIdClient(customer.getIdClient());
    }

    public List<Customer> findByName(String fullName) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getFullName().equalsIgnoreCase(fullName)).toList();
    }
    public List<Customer> findByEmail(String email) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email)).toList();
    }










}
