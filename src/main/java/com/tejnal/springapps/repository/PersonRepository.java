package com.tejnal.springapps.repository;


import com.tejnal.springapps.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> getByPersonIdAndName(long personId, String name);
}
