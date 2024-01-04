package academy.devgus.storemanagement.controller;

import academy.devgus.storemanagement.mapper.ProductMapper;
import academy.devgus.storemanagement.request.ProductPostRequest;
import academy.devgus.storemanagement.request.ProductPutRequest;
import academy.devgus.storemanagement.response.ProductGetResponse;
import academy.devgus.storemanagement.response.ProductPostResponse;
import academy.devgus.storemanagement.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"v1/product", "v1/product/"})
@RequiredArgsConstructor
public class ProductController {
    private final ProductMapper mapper;
    private final ProductService productService;

    @GetMapping(path = "/p-name")
    public ResponseEntity<List<ProductGetResponse>> filterProductByName(@RequestParam(required = false) String name) {
        log.info("Request received to verify in the product list with param name '{}'", name);
        var productFound = productService.findByName(name);
        var response = mapper.toListProductGetResponse(productFound);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/p-all")
    public ResponseEntity<List<ProductGetResponse>> findAllProductAvailableInList() {
        log.info("Request to return all the list of the product, no param requested");
        var productsList = productService.findAll();
        var response = mapper.toListProductGetResponse(productsList);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/p-id/{id}")
    public ResponseEntity<ProductGetResponse> findProductById(@PathVariable Long id) {
        log.info("Requested receive to search the product, param id '{}'", id);
        var productFound = productService.findById(id);
        var response = mapper.toProductGetResponseOptional(productFound);
        return ResponseEntity.ok(response);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-version=v1")
    public ResponseEntity<ProductPostResponse> addProduct (@RequestBody ProductPostRequest productRequest) {
        var product = mapper.toProduct(productRequest); //product in ProductPostResponse form
        product = productService.save(product); //saving the product passed throw the mapper and the save from the service (are the same)
        var response = mapper.toProductPostResponse(product); //catching the response
        return ResponseEntity.status(HttpStatus.CREATED).body(response); //returned that it was created and the body will see the new product created
    }
    @PutMapping(path = "/p-update")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductPutRequest productRequest) {
        log.info("Request receive to update the product '{}'", productRequest);

        var productToUpdate = mapper.toProduct(productRequest);
        productService.update(productToUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/p-delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        log.info("Request receive to delete the product by the param id. '{}'", id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
