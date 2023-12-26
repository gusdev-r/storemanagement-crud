package academy.devgus.storemanagement.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerPostRequest { //creation(verb) it's not necessary to put a constructor here
    private String fullName;
    private Long idClient;
    private String cpf;
    private String email;
}
