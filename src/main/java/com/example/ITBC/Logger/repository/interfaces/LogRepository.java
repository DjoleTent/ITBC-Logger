package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository  extends JpaRepository<Log, UUID> {

//    void insertLog(Log log);
}
