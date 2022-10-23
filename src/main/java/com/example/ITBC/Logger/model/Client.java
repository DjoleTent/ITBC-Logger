package com.example.ITBC.Logger.model;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Clients")
public class Client {
    @Id
    public int id;
    public String username;
    public String password;
    public String email;
}
