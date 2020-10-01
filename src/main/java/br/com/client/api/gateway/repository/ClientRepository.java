package br.com.client.api.gateway.repository;

import br.com.client.api.gateway.repository.entity.ClientEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

    List<ClientEntity> findByNameAndCpf(final String name, final String cpf, Pageable pageable);

}
