package com.example.duanlon.repository;

import com.example.duanlon.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {
    //theo ten
    @Query("select p from Person p where p.username like %?1%")
    Optional<Person> findByUsername(String username);
    //ten day du
    @Query("select p from Person p where p.firstName=:fn and p.lastName=:ln")
    Optional<Person> findByCompleteName(@Param("fn")String fn,
                                        @Param("ln")String lastName);

}
