package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository  extends JpaRepository<Log, UUID> {

    @Override
    List<Log> findAll();
}
