package academy.devgus.storemanagement.domain;

import academy.devgus.storemanagement.repository.ProductData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
public class Product {
//    @JsonProperty(value = "name") - all the values be referenced to name
    private String name;
    private int price;
    private Long id;
}
