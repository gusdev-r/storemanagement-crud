package academy.devgus.storemanagement.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @EqualsAndHashCode.Include
    @NotBlank
    @NotEmpty
    private String fullName;
//    @EqualsAndHashCode.Include - It's used to specify some item that u want to include at the equals and hashC
    @NotBlank
    private Long idClient;
    @Size(min = 11, max = 11)
    @NotBlank
    private Long cpf;
    @NotBlank
    private String email;
    private LocalDateTime createdAt;
    private List<Product> orders = new ArrayList<>();


}
