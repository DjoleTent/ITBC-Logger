package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Client;
import com.example.ITBC.Logger.model.Log;
import com.example.ITBC.Logger.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    @Override
    List<Log> findAll();

    Log findByToken(String token);

//    @Query("SELECT nl FROM NEWLOGS nl WHERE (:message is null or nl.message = :message) and (:logType is null or nl.logType = :logType)")
//    List<Log> getLogsByParam(@Param("message") String message, @Param("logType") int LOGTYPE);

}
