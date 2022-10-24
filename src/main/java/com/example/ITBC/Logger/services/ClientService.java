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
        return clientRepository.findAll();
    }



    public ResponseEntity<Void> insertClient(Client client) {
        if (clientRepository.isDuplicateName(client.getUsername()) != 0 || clientRepository.isDuplicateName(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }


        if (!client.getEmail().contains("@") || !client.getEmail().contains(".") || client.getEmail().length() < 5
                || client.getUsername().length() < 3 || client.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        client.setId(UUID.randomUUID());
        clientRepository.save(client);


        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


}
