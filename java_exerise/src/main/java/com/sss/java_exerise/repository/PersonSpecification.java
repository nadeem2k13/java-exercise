package com.sss.java_exerise.repository;

import com.sss.java_exerise.entity.Person;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

    // Specification for filtering by name
    public static Specification<Person> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true, no filter
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    // Specification for filtering by age
    public static Specification<Person> hasAge(Integer age) {
        return (root, query, criteriaBuilder) -> {
            if (age == null) {
                return criteriaBuilder.conjunction(); // Always true, no filter
            }
            return criteriaBuilder.equal(root.get("age"), age);
        };
    }
}

