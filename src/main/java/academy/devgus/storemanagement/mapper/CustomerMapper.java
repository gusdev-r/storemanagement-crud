package academy.devgus.storemanagement.mapper;

import academy.devgus.storemanagement.domain.Customer;
import academy.devgus.storemanagement.request.CustomerPostRequest;
import academy.devgus.storemanagement.request.CustomerPutRequest;
import academy.devgus.storemanagement.response.CustomerGetResponse;
import academy.devgus.storemanagement.response.CustomerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    //transform the item (id) in a domain (map domain objects)
    //@Mapping(target = "idClient", expression = "java(java.time.LocalDateTime.now)")

    //working together with request and response at the server
    Customer toCustomer(CustomerPostRequest request);
    CustomerPostResponse toCustomerPostResponse (Customer customer);
    //it'll return all the customer by the verb get at the postman sending a request
    List<CustomerGetResponse> toListCustomerGetResponse(List<Customer> customers);
    Customer toCustomer (CustomerPutRequest request); //option to update or something like that

    CustomerGetResponse toCustomerGetResponseOptional(Optional<Customer> customer);


}
