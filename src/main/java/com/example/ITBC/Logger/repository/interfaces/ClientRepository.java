package com.example.ITBC.Logger.repository.interfaces;

import com.example.ITBC.Logger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(value = "SELECT * FROM CLIENTS", nativeQuery = true)
    List<Client> getAllClients();

    @Query(value = "SELECT COUNT(*) FROM CLIENTS WHERE username=:username", nativeQuery = true)
    Integer isDuplicateName(@Param("username") String username);

}
