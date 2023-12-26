package academy.devgus.storemanagement.repository;

import academy.devgus.storemanagement.domain.Customer;
import academy.devgus.storemanagement.domain.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerData {
    private Product product1 = Product.builder().id(020L).name("Vans Knu Skool black").price(660).build();
    private Product product2 = Product.builder().id(021L).name("iPhone 15 Pro").price(7980).build();
    private Product product3 = Product.builder().id(022L).name("Lakai Cambridge Black reflective").price(343).build();
    private Product product4 = Product.builder().id(023L).name("Nike SB Blue").price(499).build();
    private Product product5 = Product.builder().id(024L).name("Dell G15 RTX 3060").price(5890).build();
    private Product product6 = Product.builder().id(025L).name("Black baggy jeans ").price(89).build();
    private final List<Customer> customersList = new ArrayList<>(); // will never be changed (final)


    {
        var c1 = Customer.builder().cpf("92839102291").email("cesarrodrigues3@gmail.com").fullName("César Rodrigues de Souza")
                .idClient(001L).orders(List.of(product4, product3)).createdAt(LocalDateTime.now()).build();
        var c2 = Customer.builder().cpf("53285421851").email("gustavoxtr3m3@gmail.com").fullName("Gustavo Henrique Moreira")
                .idClient(002L).orders(List.of(product4, product1)).createdAt(LocalDateTime.now()).build();
        var c3 = Customer.builder().cpf("78892716241").email("rogerguedes10@gmail.com").fullName("Roger Guedes Silva")
                .idClient(003L).orders(List.of(product6, product5)).createdAt(LocalDateTime.now()).build();
        var c4 = Customer.builder().cpf("10392328128").email("renato08aug@gmail.com").fullName("Renato Almeida Vasconcelos")
                .idClient(004L).orders(List.of(product1, product2)).createdAt(LocalDateTime.now()).build();
        customersList.addAll(List.of(c1, c2, c3, c4));
    }
    public List<Customer> getCustomersList() {
        return customersList;
    }
}
