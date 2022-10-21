package com.example.ITBC.Logger.services;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.interfaces.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public ResponseEntity<Void> insertClient(Client client) {
        if (clientRepository.isDuplicateName(client.getUsername()) != 0 || clientRepository.isDuplicateName(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        for( var clientOne : clientRepository.getAllClients()) {
            if (!clientOne.getEmail().contains("@") || !clientOne.getEmail().contains(".") || clientOne.getEmail().length()<5
            || clientOne.getUsername().length()<3 || clientOne.getPassword().length()<8) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

//        client.setId(UUID.randomUUID());

//        clientRepository.getAllClients().add(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    //email must be valid
    //username at least 3 characters
    //password at least 8 characters and one letter and one number
}
