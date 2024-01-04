package academy.devgus.storemanagement.controller;

import academy.devgus.storemanagement.mapper.CustomerMapper;
import academy.devgus.storemanagement.request.CustomerPostRequest;
import academy.devgus.storemanagement.request.CustomerPutRequest;
import academy.devgus.storemanagement.response.CustomerGetResponse;
import academy.devgus.storemanagement.response.CustomerPostResponse;
import academy.devgus.storemanagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"v1/customer", "v1/customer/"}) //that's the url to search
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerMapper mapper;
    //@Autowired
    private final CustomerService customerService;

    @GetMapping(path = "/c-name")
    public ResponseEntity<List<CustomerGetResponse>> filterListByName(@RequestParam(required = false) String name) {
        log.info("Request received to list the customer list, param name '{}'", name);
        var customers = customerService.findByName(name);
        var response = mapper.toListCustomerGetResponse(customers);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/c-all")
    public ResponseEntity<List<CustomerGetResponse>> findAllListOfCustomer() {
        log.info("Request receive to list all the customer, without parameters");
        var customers = customerService.findAll();
        var response = mapper.toListCustomerGetResponse(customers);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/c-cpf/{cpf}")
    public ResponseEntity<CustomerGetResponse> findCustomerByCpf(@PathVariable Long cpf) {
        log.info("Request received to list the customer list, param '{}'", cpf);
        var customersFound = customerService.findByCpf(cpf);
        var response = mapper.toCustomerGetResponse(customersFound);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/c-email")
    public ResponseEntity<List<CustomerGetResponse>> findCustomerByEmail(@RequestBody(required = false) String email) {
        log.info("Request receive to find the customer in the list, param '{}'", email);
        var customerFound = customerService.findByEmail(email);
        var response = mapper.toListCustomerGetResponse(customerFound);
        return ResponseEntity.ok(response);
    }
    @GetMapping (path = "/c-id/{id}")
    public ResponseEntity<CustomerGetResponse> findListById(@PathVariable Long id) {
        log.info("Request received to list the customer list, param '{}'", id);
        var customerFound = customerService.findById(id);
        var response = mapper.toCustomerGetResponse(customerFound);
        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
    headers = "x-api-version=v1")
    public ResponseEntity<CustomerPostResponse> addCustomer(@RequestBody @Valid CustomerPostRequest request) {
        var customer = mapper.toCustomer(request);
        customer = customerService.save(customer); //with the post the customer will be created
        var response = mapper.toCustomerPostResponse(customer); //this is what will be sent to the server (response)
        log.info("Creating a new Customer '{}'", request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); //the response is the json created
    }
    @DeleteMapping(path = "/c-delete/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        log.info("Request receive to delete the customer with id '{}'", id);

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }
    //there's a problem here
    @PutMapping(path = "/c-update/{id}")
    public ResponseEntity<CustomerGetResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerPutRequest request) {
        log.info("Request received to update the customer '{}'", request);
        var customerToUpdate = mapper.toCustomer(request);
        customerService.update(id, customerToUpdate);
        log.info("All the updates was successful");
        return ResponseEntity.noContent().build();
    }
}
