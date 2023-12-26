package academy.devgus.storemanagement.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductPostResponse {
    private String name;
    private int price;
    private Long id;
}
