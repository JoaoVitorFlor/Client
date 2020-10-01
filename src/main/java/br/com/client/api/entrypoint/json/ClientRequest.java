package br.com.client.api.entrypoint.json;

import java.time.LocalDate; 
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ClientRequest {

    private String name;

    @CPF
    private String cpf;

    private LocalDate birthDate;
}
