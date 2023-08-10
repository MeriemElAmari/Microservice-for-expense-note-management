package com.novelis.novy.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Long id;

    @Column(name = "department_name", length = 50)
    private String departmentName;

    @Column(name = "description", length = 255)
    private String description;
}