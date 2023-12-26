package academy.devgus.storemanagement.mapper;

import academy.devgus.storemanagement.domain.Product;
import academy.devgus.storemanagement.request.ProductPostRequest;
import academy.devgus.storemanagement.request.ProductPutRequest;
import academy.devgus.storemanagement.response.ProductGetResponse;
import academy.devgus.storemanagement.response.ProductPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    //save -> Response
    Product toProduct (ProductPostRequest request);
    //save -> Request
    ProductPostResponse toProductPostResponse (Product product); //sending the response (returning a product)
    //update -> Request -- the response is void
    Product toProduct (ProductPutRequest request);

    List<ProductGetResponse> toListProductGetResponse (List<Product> products);
    ProductGetResponse toProductGetResponse (Product product);
    ProductGetResponse toOptionalProductGetResponse(Optional<Product> product); //the usability of optional is because the product can't exist


    Product toProduct(Product product);
    ProductGetResponse toProductGetResponseOptional(Optional<Product> product);
}
