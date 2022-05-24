package main.DAO.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"department"}, allowSetters = true)
    private List<Employee>employees;


}
