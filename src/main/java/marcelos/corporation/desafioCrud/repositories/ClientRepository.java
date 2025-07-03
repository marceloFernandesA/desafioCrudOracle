package marcelos.corporation.desafioCrud.repositories;

import marcelos.corporation.desafioCrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
