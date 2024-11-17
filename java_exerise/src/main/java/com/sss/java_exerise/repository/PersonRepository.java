package com.sss.java_exerise.repository;

import com.sss.java_exerise.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> , JpaSpecificationExecutor<Person> {
// Custom queries can go here if needed


}
