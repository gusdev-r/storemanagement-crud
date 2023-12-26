package academy.devgus.storemanagement.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPostRequest { //verify if the variable will be created
    private String name;
    private int price;
    private Long id;
}
