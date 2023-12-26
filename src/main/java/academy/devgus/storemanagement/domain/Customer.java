package academy.devgus.storemanagement.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    private String fullName;
//    @EqualsAndHashCode.Include - It's used to specify some item that u want to include at the equals and hashC
    private Long idClient;
    private String cpf;
    private String email;
    private LocalDateTime createdAt;
    private List<Product> orders = new ArrayList<>();

    public List<Product> getOrders() {
        return orders;
    }
}
