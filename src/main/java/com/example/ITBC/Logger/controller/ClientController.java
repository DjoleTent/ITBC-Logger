package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.services.ClientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
}
