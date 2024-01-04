package academy.devgus.storemanagement.controller;

import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.mapper.CPMapper;
import academy.devgus.storemanagement.request.ProductPostRequest;
import academy.devgus.storemanagement.request.ProductPutRequest;
import academy.devgus.storemanagement.response.ProductGetResponse;
import academy.devgus.storemanagement.response.ProductPostResponse;
import academy.devgus.storemanagement.service.CPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"v1/cp", "v1/cp/"})
@RequiredArgsConstructor
public class CPController {
    private final CPMapper cpMapper;

    private final CPService cpService;

    @GetMapping(path = "/c-name-to-p")
    public ResponseEntity<List<ProductGetResponse>> findOrdersByCustomerName(@RequestParam(required = false) String fullName) {
        log.info("Request receive to verify all the orders realized by the customer with param name '{}'", fullName);
        var listFromCustomer = cpService.findOrdersByCustomerName(fullName);
        var response = cpMapper.toListProductGetResponse(listFromCustomer);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/c-cpf-to-p")
    public ResponseEntity<List<ProductGetResponse>> findOrdersByCustomerCpf(@RequestParam(required = false) String cpf) {
        log.info("Request receive to verify all the orders realized by the customer with param name '{}'", cpf);
        var listFromCustomer = cpService.findOrdersByCustomerCpf(cpf);
        var response = cpMapper.toListProductGetResponse(listFromCustomer);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/c-email-to-p") //returning all the orders that was searched by email of the customer
    public ResponseEntity<List<ProductGetResponse>> findOrdersByCustomerEmail(@RequestParam(required = false) String email) {
        log.info("Request receive to verify all the orders realized by the customer with param email '{}'", email);
        var listFromCustomer = cpService.findOrdersByCustomerEmail(email);
        var response = cpMapper.toListProductGetResponse(listFromCustomer);
        return ResponseEntity.ok(response);
    }

    //try to use this code for specific search about the orders of the customer

    //I don't know if the best position is to treat if an empty Optional passes by
    //@GetMapping(path = "/c-id-to-p") //returning a specific order searched by the customerId and found by the productId
    //public ResponseEntity<ProductGetResponse>findOrdersByCustomerId(@PathVariable Long customerId, Long productId) {
    //    log.info("Request receive to verify all the orders realized by the customer with param id '{}'", customerId);
    //    var listFromCustomer = cpService.findOrdersByTheCustomerId(customerId, productId);
    //    var response = cpMapper.toProductGetResponseOptional(listFromCustomer);
    //    return ResponseEntity.ok(response);
    //}
    @GetMapping(path = "/c-id-to-p")
    public ResponseEntity<List<ProductGetResponse>> findOrdersByCustomerId(@PathVariable Long id) {
        log.info("Request receive to verify all the orders realized by the customer with param id '{}'", id);
        var listFromCustomer = cpService.findAllOrdersByCustomerId(id);
        var response = cpMapper.toListProductGetResponse(listFromCustomer);
        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-version=v1")
    public ResponseEntity<ProductPostResponse> addOrderToTheCustomer(@RequestBody Long idCustomer, ProductPostRequest product) {
        Product productToAdd = cpMapper.toProductToPostRequest(idCustomer, product);
        productToAdd = cpService.saveOrders(idCustomer, productToAdd);
        var response = cpMapper.toProductPostResponse(productToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping(path = "/c-delete-to-p")
    public ResponseEntity<Void> deleteOrderByCustomerId(@PathVariable Long customerId, Long productId) {
        log.info("Request receive to delete the order by param id '{}', realized by the customer with id '{}'", productId, customerId);
        cpService.delete(customerId, productId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "/c-update-to-p")
    public ResponseEntity<Void> updateOrderByCustomerId(@PathVariable Long customerId, Long productId, ProductPutRequest productToUpdate) {
        log.info("Request receive to update the product of id '{}' by the customer with id '{}'", productId, customerId);
        Product pToUpdate = cpMapper.toProduct(productToUpdate);
        cpService.updateOrders(customerId, productId, pToUpdate);
        return ResponseEntity.noContent().build();
    }

}
