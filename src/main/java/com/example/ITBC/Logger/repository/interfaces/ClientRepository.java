package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAll();


    @Query(value = "SELECT COUNT(*) FROM USERS WHERE username=:username", nativeQuery = true)
    Integer isDuplicateName(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE password=:password", nativeQuery = true)
    Integer isExistPassword(@Param("password") String password);

    @Query(value = "SELECT * FROM USERS WHERE id = :id", nativeQuery = true)
    Client isIdExist(UUID id);

    @Query(value = "SELECT u.id as id, u.username as username, u.email as email, COUNT(nl.id) as logCount FROM USERS u JOIN NEWLOGS nl ON u.username=nl.token", nativeQuery = true)
    List<?> clientLogCount();

    @Query(value = "SELECT COUNT(*) FROM USERS u JOIN NEWLOGS nl ON nl.token=u.username where nl.token=:token", nativeQuery = true)
    int getAllLogsByClient(String token);

}
