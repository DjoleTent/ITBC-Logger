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

    public ResponseEntity<Void> createLog(HttpServletRequest request, Log log) {
        Log newLog = new Log(UUID.randomUUID(),log.getMessage(),log.getLogType(),java.time.LocalDate.now(),"token");
        log.setCreatedDate(java.time.LocalDate.now());
        String token = null;
//        HttpSession session = request.getSession();
//        token = (String) session.getAttribute("token");
        List<Client> svi = clientRepository.findAll();
        token=svi.get(svi.size() - 1).getUsername();
        log.setToken(token);
        log.setId(UUID.randomUUID());

        System.out.println(svi.get(svi.size() - 1).getUsername());
        System.out.println(log.getCreatedDate());
//        System.out.println(session);
        System.out.println(log);

        if (log.getLogType()>2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (log.getMessage().length()>1024){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        }
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        newLog.setToken(token);
        logRepository.save(newLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
