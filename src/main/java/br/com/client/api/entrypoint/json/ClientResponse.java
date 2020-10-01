package br.com.client.api.entrypoint.json;

import java.time.LocalDate; 
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClientResponse {

    private final long id;
    private final String name;
    private final String cpf;
    private final LocalDate birthDate;
    private final long age;
}
