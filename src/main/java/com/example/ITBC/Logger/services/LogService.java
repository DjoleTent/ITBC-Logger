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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

        log.setDatum(LocalDateTime.now().toString());

        log.setToken(token);

        log.setId(UUID.randomUUID());

        if (log.getLOGTYPE() > 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (log.getMessage().length() > 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        }

        System.out.println(clientRepository.isDuplicateName(token));

        if (clientRepository.isDuplicateName(token) != 1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        log.setToken(token);
        System.out.println(log);
        logRepository.save(log);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public ResponseEntity<Log> searchLogs(String dateFrom, String dateTo, String message, int logType, String token) {

        System.out.println(token);
        Log newLog = new Log();
        for (var log : logRepository.findAll()) {
            if (log.getToken().equals(token)) {
                newLog = log;
            }
        }


        if (newLog == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//        LocalDate dateF = LocalDate.parse(dateFrom, formatter);
//        LocalDate dateT = LocalDate.parse(dateTo, formatter);
//        LocalDate dateNow = LocalDate.parse(newLog.getDatum(), formatter);


//        if(dateNow.isAfter(dateT) || dateNow.isBefore(dateF) || logType>2 || !newLog.getMessage().contains(message)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
        if (logType > 2 || !newLog.getMessage().contains(message)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        return ResponseEntity.status(HttpStatus.OK).body(newLog);
    }

}
