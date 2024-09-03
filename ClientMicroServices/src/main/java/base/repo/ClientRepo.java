package base.repo;

import base.dto.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByClientName(String clientName);
}
