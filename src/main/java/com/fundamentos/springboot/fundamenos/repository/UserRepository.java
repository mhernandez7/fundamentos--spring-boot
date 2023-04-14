package com.fundamentos.springboot.fundamenos.repository;

import com.fundamentos.springboot.fundamenos.entity.User;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    //Sort es una implementacion de ordenamiento de data
    @Query("SELECT u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);


}
