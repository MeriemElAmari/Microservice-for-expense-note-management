package com.novelis.novy.model;

import com.novelis.novy.enums.ApprovalStatus;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXPENSES_REPORTS")
public class ExpenseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense_report")
    private Long reportID;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApprovalStatus approvalStatus;

    @Column(name = "receipt", length = 255)
    private String receipt;

    @ManyToOne
    @JoinColumn(name = "id_mission",
    foreignKey = @ForeignKey(
            name = "fk_expensesReport_mission"
    ))
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "id",
            foreignKey = @ForeignKey(name = "fk_expenseReport_collaborator"))
    private Collaborator collaborator;
}

