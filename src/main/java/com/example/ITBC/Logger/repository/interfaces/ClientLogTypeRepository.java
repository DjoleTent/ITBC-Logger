package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.ClientLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientLogTypeRepository extends JpaRepository<ClientLogType, UUID> {
}
