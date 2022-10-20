package com.example.ITBC.Logger.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
