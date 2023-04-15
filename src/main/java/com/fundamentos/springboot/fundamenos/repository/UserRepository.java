package com.fundamentos.springboot.fundamenos.repository;

import com.fundamentos.springboot.fundamenos.entity.User;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    //Sort es una implementacion de ordenamiento de data

    @Query("SELECT u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Query Methods
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    //Query Methods con Like
    List<User> findByNameLike(String name);
    //Query Methods con Or
    List<User> findByNameOrEmail(String name, String email);

    //Query methods con busqueda por fecha
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);
    //Query methods con busqueda por nombre orden por id
    //Tambien se puede con Containing cambiando el nombre a findByNameContainingOrderByIdDesc
    //con lo que se evida traer en el metodo like las designaciones %%
    List<User> findByNameLikeOrderByIdDesc(String name);
}
