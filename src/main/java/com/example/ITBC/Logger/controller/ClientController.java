package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.model.ClientLogType;
import com.example.ITBC.Logger.repository.interfaces.ClientRepository;
import com.example.ITBC.Logger.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/api/clients/noadmin")
    public List<Client> showAllClients() {
        return clientService.getAllClients();
    }
    @GetMapping("/api/clients")
    public ResponseEntity<List<ClientLogType>> showAllClientsByAdmin(@RequestHeader(value="Authorization") String token) {
        if(clientRepository.isDuplicateName(token)==0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        // ZA ADMINA JE UZET JEDAN KONKRETAN KLIJENT I NJEGOV TOKEN JE ADMIN TOKEN
        if(clientRepository.isDuplicateName(token)==1 && !token.equals("userpokusaj9")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<ClientLogType> listCLT = new ArrayList<>();
        for(var client:clientRepository.findAll()){
            listCLT.add(new ClientLogType(client.getId(),client.getUsername(),client.getEmail(),clientRepository.getAllLogsByClient(client.getUsername())));
        }


        return ResponseEntity.status(HttpStatus.OK).body(listCLT);
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        return clientService.insertClient(client);
    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<Map> loginClient(@RequestBody Client client) {
        return clientService.loginAccount(client.getUsername(), client.getPassword(),client.getEmail());
    }


    @PatchMapping("/api/clients/{id}/reset-password")
    public ResponseEntity<Void> resetPassword(@PathVariable(value = "id") UUID id, @RequestHeader(value="Authorization") String token, @RequestBody Client client){

        if(clientRepository.isDuplicateName(token)==0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        if(clientRepository.isDuplicateName(token)==1 && !token.equals("userpokusaj9")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }


        clientRepository.isIdExist(id).setPassword(client.getPassword());
        clientRepository.save(clientRepository.isIdExist(id));
        System.out.println(clientRepository.isIdExist(id));


        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
