package com.example.ITBC.Logger.services;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.repository.interfaces.ClientRepository;
import com.example.ITBC.Logger.repository.interfaces.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public LogService(LogRepository logRepository, ClientRepository clientRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Void> createLog(Log log, String token) {

        System.out.println("----------------------------");
        System.out.println(token);
        System.out.println("----------------------------");

        log.setDatum(LocalDateTime.now().toString());

        log.setToken(token);

        log.setId(UUID.randomUUID());

        if (log.getLOGTYPE() > 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (log.getMessage().length() > 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        }
        System.out.println("++++++++++");
        System.out.println(clientRepository.isDuplicateName(token));
        System.out.println("++++++++++");

        if (clientRepository.isDuplicateName(token)!=1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        log.setToken(token);
        System.out.println(log);
        logRepository.save(log);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }
    public List<Log> getAllLogs(){
       return logRepository.findAll();
    }
}
