package com.example.ITBC.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Table(name = "CLIENTLOGTYPE")
public class ClientLogType {

    @Id
    private UUID id;
    private String username;
    private String email;
    private int logCount;
}
