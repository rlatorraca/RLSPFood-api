package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.CityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Client;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.ClientRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientRegistrationService {

    public static final String MSG_CLIENT_AS_CODE_IS_NOT_FOUND_INTO_DATABASE = "Client having code %d  %d not found into the Database";
    public static final String MSG_CLIENT_CANNOT_BE_REMOVED_USED_AS_SECONDARY_KEY = "Client having code %d cannot be removed, because that is being used as  secondary key";
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @Transactional
    public Client save(Client client){
        clientRepository.detach(client);
        Optional<Client> clientFound = clientRepository.findByEmail(client.getEmail());

        if( clientFound.isPresent() && !clientFound.get().equals(client)) {
            throw new GenericBusinessException(String.format("Email %s already used by another client", client.getEmail()));
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void remove(Long id){
        try{
            clientRepository.deleteById(id);
            clientRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new CityNotFoundException(
                    String.format(MSG_CLIENT_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityIsForeignKeyException(
                    String.format(MSG_CLIENT_CANNOT_BE_REMOVED_USED_AS_SECONDARY_KEY, id)
            );
        }
    }

    @Transactional
    public void changePassword(Long clientId, String currentPassword, String newPassword) {
        Client usuario = findOrFail(clientId);

        if (usuario.passwordNotMatches(currentPassword)) {
            throw new GenericBusinessException("Passwords not matches.");
        }

        usuario.setPassword(newPassword);
    }

    public List<Client> listAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id){
        try{
            return  clientRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(
                    String.format(MSG_CLIENT_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, id)
            );
        }
    }

    public Client findOrFail(Long clientId){
        return clientRepository.findById(clientId).orElseThrow(()-> new CityNotFoundException(String.format(MSG_CLIENT_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, clientId)));
    }
}

