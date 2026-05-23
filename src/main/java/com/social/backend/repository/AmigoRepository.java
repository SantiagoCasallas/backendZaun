package com.social.backend.repository;

import com.social.backend.model.Amigo;
import com.social.backend.model.AmigoId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmigoRepository extends JpaRepository<Amigo, AmigoId> {

    @Query(value = "SELECT * FROM amigo WHERE consecuser = :id OR consecuser2 = :id", nativeQuery = true)
    List<Amigo> buscarAmigosDe(@Param("id") String consecUser);

    @Modifying
    @Query(value = "INSERT INTO amigo (consecuser, consecuser2) VALUES (:usuario, :amigo)", nativeQuery = true)
    void insertarAmigo(@Param("usuario") String consecUser, @Param("amigo") String consecUser2);
}