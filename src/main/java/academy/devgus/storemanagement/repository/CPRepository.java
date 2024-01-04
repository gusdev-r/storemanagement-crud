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
public class CPRepository {
    private final CustomerRepository cRepository;

    private final CustomerData cData;

    public List<Customer> findByName (String name) {
        return cRepository.findByName(name);
    }

    public List<Customer> findByEmail(String email) {
        return cData.getCustomersList().stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email)).toList();
    }
    public Optional<Customer> findByCpf(Long cpf) {
        return cData.getCustomersList().stream().filter(customer -> customer.getCpf().equals(cpf)).findFirst();
    }

    public Optional<Customer> findById(Long id) {
        return cData.getCustomersList().stream().filter(customer -> customer.getIdClient().equals(id)).findFirst();
    }
    //this method needs to changed to the customerProductClass
    public void updateOrderFromCustomer(String customerName, Long productId, Product updatedProduct) {
        Customer c = (Customer) findByName(customerName);
        if (c != null) {
            List<Product> pList = c.getOrders();
            for (Product product : pList) {
                if (product.getId() == productId) {
                    product.setPrice(updatedProduct.getPrice());
                    product.setName(updatedProduct.getName());
                    break;
                }
            }
        }
    }

    //this method needs to changed to the customerProductClass
    //I don't think so if it is removing the product of the original list (c.getOrders()) *see this code again*
    public void removeProductFromClient(String customerName, Long productId) {
        Customer c = (Customer) findByName(customerName);
        if (c != null) {
            List<Product> ordersFromClient = c.getOrders();
            ordersFromClient.removeIf(product -> product.getId().equals(productId));
        }
    }
    public List<Product> findOrdersByTheCustomerName(String fullNameCustomer) {
        Customer c = (Customer) findByName(fullNameCustomer);
        return c.getOrders();
    }
    public List<Product> findOrdersByTheCustomerEmail(String email) {
        Customer c = (Customer) findByEmail(email);
        return c.getOrders();
    }
    public List<Product> findOrdersByTheCustomerCpf (Long cpf) {
        Optional<Customer> c = findByCpf(cpf);
        return c.get().getOrders();
    }
    public List<Product> findAllOrdersByTheCustomerId(Long idCustomer) {
        Optional<Customer> c = findById(idCustomer);
        Customer customer = c.orElseThrow(() -> new RuntimeException("The search go bad, try something later."));
        return customer.getOrders();
    }
    public Optional<Product> findOrdersByTheCustomerId(Long idCustomer, Long idProduct) {
        Optional<Customer> c = findById(idCustomer);
        Customer customer = c.get();//orElseThrow(() -> new RuntimeException("The search go bad, try something later."));
        return customer.getOrders().stream().filter(p -> p.getId().equals(idProduct)).findFirst();
    }


    //search the customer and after search the specified order/product in the orders list (search on customer.getOrders)
    public Product findTheProductInTheListOfClient(String clientName, Long productId) {
        Customer c = (Customer) findByName(clientName);
        List<Product> listOfOrders = c.getOrders();
        //return c.getOrders().stream().filter(product -> product.getId().equals(productId)).findFirst();
        Optional<Product> p = listOfOrders.stream().filter(product -> product.getId().equals(productId)).findFirst();
        return p.get();  //orElseThrow(() -> new RuntimeException("The search go bad, try something later."));
    }
    public Product saveOrdersInTheListFromCustomer(Long idCustomer, Product product) {
        var customerList = findAllOrdersByTheCustomerId(idCustomer);
        customerList.add(product);
        return product;
    }
}
