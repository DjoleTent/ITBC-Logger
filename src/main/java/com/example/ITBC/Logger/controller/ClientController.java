package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.services.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("api/v1/clients")
    public List<Client> showAllClients(){
        return clientService.getAllClients();
    }
}
