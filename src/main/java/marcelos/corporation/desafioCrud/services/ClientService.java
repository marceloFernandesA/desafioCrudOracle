package marcelos.corporation.desafioCrud.services;

import jakarta.persistence.EntityNotFoundException;
import marcelos.corporation.desafioCrud.dto.ClientDto;
import marcelos.corporation.desafioCrud.entities.Client;
import marcelos.corporation.desafioCrud.repositories.ClientRepository;
import marcelos.corporation.desafioCrud.services.exeception.DataBaseException;
import marcelos.corporation.desafioCrud.services.exeception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado"));
        ClientDto dto = new ClientDto(client);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findByAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDto(x));
    }

    @Transactional
    public ClientDto insert(ClientDto dto){
        Client entity = new Client();
        copyDtoEntity(dto,entity);
        entity = repository.save(entity);
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoEntity(dto,entity);
            entity = repository.save(entity);

            return new ClientDto(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) throws DataBaseException{
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade referencial");
        }

    }


    private void copyDtoEntity(ClientDto dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
