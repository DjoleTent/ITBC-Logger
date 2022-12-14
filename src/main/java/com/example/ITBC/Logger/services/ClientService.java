package com.example.ITBC.Logger.services;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.repository.interfaces.ClientRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public ResponseEntity<Map> loginAccount(String username, String password, String email) {
        var user1 = clientRepository.isDuplicateName(username);
        var pass1 = clientRepository.isExistPassword(password);
        var email1 = clientRepository.isDuplicateEmail(email);
        System.out.println(user1);
        System.out.println(pass1);
        System.out.println(email1);
        if ((user1 == 0 && email1 == 0) || pass1 == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        String acc1 = null;
        if (user1 > email1) {
            acc1 = username;
        } else {
            acc1 = email;
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", acc1);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    public List<Client> getAllClientsByAdmin() {
        return clientRepository.findAll();
    }


}
