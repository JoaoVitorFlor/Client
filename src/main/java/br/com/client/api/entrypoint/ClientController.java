package br.com.client.api.entrypoint;

import br.com.client.api.entrypoint.json.ClientRequest;
import br.com.client.api.entrypoint.json.ClientResponse;
import br.com.client.api.gateway.repository.ClientRepository;
import br.com.client.api.gateway.repository.entity.ClientEntity;
import br.com.client.api.usecase.GetAgeUsecase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

import javax.validation.constraints.Pattern;
import javax.xml.ws.Response;
import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientController {

	final GetAgeUsecase getAgeUsecase;

	final ClientRepository clientRepository;

	@PostMapping
	public ResponseEntity<?> postClient(@Valid final ClientRequest request){

		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setName(request.getName());
		clientEntity.setBirthDate(request.getBirthDate());
		clientEntity.setCpf(request.getCpf());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clientRepository.save(clientEntity));
		
	}

	@PostMapping
	public ResponseEntity<List<ClientResponse>> getClients(@RequestParam final String name,
			@RequestParam final String cpf,
			@RequestParam(name = "pageNumber", defaultValue = "0") final int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") final int pageSize){

		final Pageable pageable = PageRequest.of(pageNumber, pageSize);

		List<ClientResponse> clients = clientRepository.findByNameAndCpf(name, cpf, pageable)
				.stream()
				.map(this :: valueOf)
				.collect(Collectors.toList());

		return ResponseEntity.ok(clients);

	}


	@PutMapping("/{id}")
	public ResponseEntity<?> putClient(@PathVariable("id") Long id,
			@Valid final ClientRequest request){

		Optional<?> client = clientRepository.findById(id);

		if(client.isPresent()){

			ClientEntity clientEntity = new ClientEntity();
			clientEntity.setId(id);
			clientEntity.setName(request.getName());
			clientEntity.setBirthDate(request.getBirthDate());
			clientEntity.setCpf(request.getCpf());

			return ResponseEntity.ok(clientRepository.save(clientEntity));

		}else{
			return ResponseEntity.notFound().build();
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> patchClient(@PathVariable("id") Long id,
			@Valid final ClientRequest request){

		Optional<?> client = clientRepository.findById(id);

		if(client.isPresent()){

			ClientEntity clientEntity = new ClientEntity();
			if(request.getName() != null){
				clientEntity.setName(request.getName());
			}

			if(request.getCpf() != null){
				clientEntity.setCpf(request.getCpf());
			}


			if(request.getBirthDate() != null){
				clientEntity.setBirthDate(request.getBirthDate());
			}
			return ResponseEntity.ok(clientRepository.save(clientEntity));

		}else{
			return ResponseEntity.notFound().build();
		}
	}

	private ClientResponse valueOf(final ClientEntity client){
		return ClientResponse.builder()
				.id(client.getId())
				.name(client.getName())
				.cpf(client.getCpf())
				.birthDate(client.getBirthDate())
				.age(getAgeUsecase.execute(client.getBirthDate()))
				.build();
	}
}
