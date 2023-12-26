package academy.devgus.storemanagement.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductGetResponse {
    private String name;
    private int price;
    private Long id;
}
