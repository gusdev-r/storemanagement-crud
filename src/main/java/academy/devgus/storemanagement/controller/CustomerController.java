package academy.devgus.storemanagement.controller;

import academy.devgus.storemanagement.mapper.CustomerMapper;
import academy.devgus.storemanagement.request.CustomerPostRequest;
import academy.devgus.storemanagement.request.CustomerPutRequest;
import academy.devgus.storemanagement.response.CustomerGetResponse;
import academy.devgus.storemanagement.response.CustomerPostResponse;
import academy.devgus.storemanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"v1/customers", "v1/customers/"}) //that's the url to search
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerMapper mapper;
    private final CustomerService customerService;

    @GetMapping("ByName") //get - this is a request sent to the server (sending the list of customer to the response of the server)
    public ResponseEntity<List<CustomerGetResponse>> filterListByName(@RequestParam(required = false) String name) { //using a list here because the re
        log.info("Request received to list the customer list, param name '{}'", name); //it's necessary to pass the name at the request
        var customers = customerService.findByName(name);
        var response = mapper.toListCustomerGetResponse(customers);
        return ResponseEntity.ok(response);
    }
    @GetMapping("findALl")
    public ResponseEntity<List<CustomerGetResponse>> findAllListOfCustomer() {
        log.info("Request receive to list all the customer, without parameters");
        var customers = customerService.findAll();
        var response = mapper.toListCustomerGetResponse(customers);
        return ResponseEntity.ok(response); //the response needs to be the list of customer
    }
    @GetMapping("ByCpf")
    public ResponseEntity<List<CustomerGetResponse>> findListByCpf(@RequestBody(required = false) String cpf) {
        log.info("Request received to list the customer list, param '{}'", cpf);
        var customersFound = customerService.findByCpf(cpf);
        var response = mapper.toListCustomerGetResponse(customersFound);
        return ResponseEntity.ok(response);
    }
    @GetMapping ("{id}")
    public ResponseEntity<CustomerGetResponse> findListById(@PathVariable Long id) {
        log.info("Request received to list the customer list, param '{}'", id);
        var customerFound = customerService.findById(id);
        var response = mapper.toCustomerGetResponseOptional(customerFound);
        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
    headers = "x-api-version=v1")
    public ResponseEntity<CustomerPostResponse> addCustomer(@RequestBody CustomerPostRequest request) {
        var customer = mapper.toCustomer(request);
        customer = customerService.save(customer); //with the post the customer will be created
        var response = mapper.toCustomerPostResponse(customer); //this is what will be sent to the server (response)

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        log.info("Request receive to delete the customer with id '{}'", id);

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerPutRequest request) {
        log.info("Request received to update the customer '{}'", request);

        var customerToUpdate = mapper.toCustomer(request);
        customerService.update(customerToUpdate);

        return ResponseEntity.noContent().build();
    }
}
