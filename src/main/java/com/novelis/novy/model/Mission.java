package com.novelis.novy.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MISSIONS")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mission")
    private Long id;

    @ManyToMany(mappedBy = "missions")
    private List<Collaborator> collaborators = new ArrayList<>();

    @Column(name = "mission_name", length = 100)
    private String missionName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "mission")
    private List<ExpenseReport> expenseReports = new ArrayList<>();

}

