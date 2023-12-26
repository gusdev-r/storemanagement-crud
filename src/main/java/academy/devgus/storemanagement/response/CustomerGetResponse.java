package academy.devgus.storemanagement.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerGetResponse { //read/consult(verb)
    private String fullName;
    private Long idClient;
    private String cpf;
    private String email;
}
