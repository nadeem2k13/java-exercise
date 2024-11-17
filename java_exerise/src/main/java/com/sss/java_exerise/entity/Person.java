
package com.sss.java_exerise.entity;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
}
