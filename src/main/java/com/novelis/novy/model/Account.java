package com.novelis.novy.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_collaborator",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(
            name = "fk_collaborator_account"

    ))
    private Collaborator collaborator;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "account_type", length = 50)
    private String accountType;
}
