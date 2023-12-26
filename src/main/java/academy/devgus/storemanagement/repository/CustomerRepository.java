package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Customer;
import academy.devgus.storemanagement.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CustomerRepository {
    private CustomerData customerData;

    //if u wanna remove the client or exclude the account for example
    public void delete (Customer customer) {
        customerData.getCustomersList().remove(customer);
    }
    public List<Customer> findAll() {
        return customerData.getCustomersList();
    }
    //It'll work as an add like the example learned
    public Customer save (Customer customer) {
        customerData.getCustomersList().add(customer);
        return customer;
    }
    public void update (Customer customer) {
        delete(customer);
        save(customer);
    }
    public List<Customer> findByName(String fullName) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getFullName().equalsIgnoreCase(fullName)).toList();
    }
    public List<Customer> showAllClients () {
        return customerData.getCustomersList();
    }
    //all these methods return a customer, so if we compare the returned customer passing the same value as the cpf and the same email, it'll return the same person

    //maybe this will return an exception or an error because if there's other people with the first and the user search it
    //only with the first name , What is it gonna return ?

    public Optional<Customer> findById(Long id) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getIdClient().equals(id)).findFirst();
    }
//    public Customer findById(Long id) {
//        return customerData.getCustomersList().stream().filter(customer -> customer.getIdClient().equals(id)).findFirst();
//    }
    public List<Customer> findByEmail(String email) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email)).toList();
    }
    public List<Customer> findByCpf(String cpf) {
        return customerData.getCustomersList().stream().filter(customer -> customer.getCpf().equals(cpf)).toList();
    }









}
