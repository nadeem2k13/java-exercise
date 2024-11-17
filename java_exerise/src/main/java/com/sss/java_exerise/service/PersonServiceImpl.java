package com.sss.java_exerise.service;


import com.sss.java_exerise.entity.Person;
import com.sss.java_exerise.repository.PersonRepository;
import com.sss.java_exerise.repository.PersonSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private PersonRepository personRepository;
    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }
    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
    @Override
    public Person updatePerson(Long id, Person person) {
        if (personRepository.existsById(id)) {
            person.setId(id);
            return personRepository.save(person);
        }
        return null; // Or throw an exception if the entity is not found
    }
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
    @Override
    public Page<Person> searchPersons(String name, Integer age, int page, int size) {
        // Build the Pageable object with page number and size
        Pageable pageable = PageRequest.of(page, size);

        // Create the specification for dynamic query
        Specification<Person> spec = Specification
                .where(PersonSpecification.hasName(name))
                .and(PersonSpecification.hasAge(age));

        // Return paginated results based on the specification
        return personRepository.findAll(spec, pageable);
    }

}
