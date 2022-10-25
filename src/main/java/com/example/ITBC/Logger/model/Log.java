package com.example.ITBC.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Logs")
public class Log {

    @Id
    private UUID Id;
    private String message;
    private int logType;
    private LocalDate createdDate;
    private String token;

}
