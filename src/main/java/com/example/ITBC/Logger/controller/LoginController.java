package com.example.ITBC.Logger.controller;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.services.ClientService;
import com.example.ITBC.Logger.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    private final LogService logService;
    private final ClientService clientService;

    public LoginController(LogService logService, ClientService clientService) {
        this.logService = logService;
        this.clientService = clientService;
    }
    @PostMapping("/api/logs/create")
    public ResponseEntity<Void> createLog(@RequestBody Log log, @RequestHeader(value="Authorization") String token){
        return logService.createLog(log, token);
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<Void> search(@RequestParam("dateFrom") String createDateFrom,
                                       @RequestParam("dateTo") String createDateTo,
                                       @RequestParam("message") String message,
                                       @RequestParam("logType") int logTypr,
                                       @RequestHeader(value="Authorization") String token){

        return null;
    }
    @GetMapping("/api/logs")
    public List<Log> showAllLogs(){
        return logService.getAllLogs();
    }
}
