package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.services.ClientService;
import com.example.ITBC.Logger.services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private final ClientService clientService;
    private final LogService logService;

    public ClientController(ClientService clientService, LogService logService) {
        this.clientService = clientService;
        this.logService = logService;
    }

    @GetMapping("/api/clients")
    public List<Client> showAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        return clientService.insertClient(client);
    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<Map> loginClient(@RequestBody Client client) {
        return clientService.loginAccount(client.getUsername(), client.getPassword());
    }

    @PostMapping("/api/logs/create")
    public ResponseEntity<Void> createLog(HttpServletRequest request,@RequestBody Log log){
        return logService.createLog(request,log);
    }


}
