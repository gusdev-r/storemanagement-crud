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
public interface CPMapper {

    CPMapper INSTANCE = Mappers.getMapper(CPMapper.class);
    //@Mapping(target = "id", expression = "java(java.time.LocalDate.now)") //using the expression to pass the id as the localDate.now
    //@Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")


    List<ProductGetResponse> toListProductGetResponse(List<Product> productList);
    ProductPostResponse toProductPostResponse(Product product);

    ProductPostRequest toProductPostRequest(Long idCustomer, ProductPostRequest product);
    Product toProductToPostRequest(Long idCustomer, ProductPostRequest product);
    Product toProduct (ProductPutRequest request);

    ProductGetResponse toProductGetResponseOptional(Optional<Product> productOptional);


}
