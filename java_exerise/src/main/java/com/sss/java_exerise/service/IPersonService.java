package com.sss.java_exerise.service;

import com.sss.java_exerise.entity.Person;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    public List<Person> getAllPersons();
    public Optional<Person> getPersonById(Long id);
    public Person createPerson(Person person);
    public Person updatePerson(Long id, Person person);
    public void deletePerson(Long id);
    public Page<Person> searchPersons(String name, Integer age, int page, int size);
}
