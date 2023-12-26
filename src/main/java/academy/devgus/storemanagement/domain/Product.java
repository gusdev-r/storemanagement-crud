package academy.devgus.storemanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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
