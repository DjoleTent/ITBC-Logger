package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    List<Client> findAll();

//    @Query(value = "SELECT * FROM USERS WHERE username=:username", nativeQuery = true)
//    Client findByUsername(String username);

//    @Query(value = "SELECT * FROM USERS WHERE email=:email", nativeQuery = true)
//    Client findByEmail(String email);


    @Query(value = "SELECT COUNT(*) FROM USERS WHERE username=:username", nativeQuery = true)
    Integer isDuplicateName(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE password=:password", nativeQuery = true)
    Integer isExistPassword(@Param("password") String password);


}
