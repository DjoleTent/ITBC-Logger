package com.example.ITBC.Logger.services;

import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.repository.interfaces.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity<Void> createLog(HttpServletRequest request, Log log) {
        log.setCreatedDate(java.time.LocalDate.now());
        String token = null;
        HttpSession session = request.getSession();
        token = (String) session.getAttribute("token");
        log.setToken(token);
        Log newLog = new Log();
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (log.getLogType()>2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (log.getMessage().length()>1024){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        }
        log.setLodId(UUID.randomUUID());
        logRepository.save(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
