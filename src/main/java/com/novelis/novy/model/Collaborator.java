package com.novelis.novy.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Collaborator")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "COLLABORATORS"
)
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false,
            nullable = false,
            name="id"
    )
    private Long idCollaborator;
    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "image", length = 255)
    private String image;


    @ManyToOne
    @JoinColumn(name = "job_id",
    foreignKey = @ForeignKey(name ="fk_collaborator_job"))
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id",
    foreignKey = @ForeignKey(
            name = "fk_collaborator_department"
    ))
    private Department department;

    @ManyToOne
    @JoinColumn(name = "agency_id",
    foreignKey = @ForeignKey(
            name = "fk_collaborator_agency"
    ))
    private Agency agency;

    @OneToMany(mappedBy = "collaborator")
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "collaborator")
    private List<ExpenseReport> expenseReports = new ArrayList<>();


}
