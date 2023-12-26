package academy.devgus.storemanagement.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProductPutRequest {
    private String name;
    private int price;
    private Long id;
}
