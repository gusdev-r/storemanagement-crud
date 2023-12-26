package academy.devgus.storemanagement.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder //the put usually have this annotation because it's used to update elements
public class CustomerPutRequest { //update elements
    private String fullName;
    private Long idClient;
    private String cpf;
    private String email;
}
