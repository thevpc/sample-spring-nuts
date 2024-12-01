package net.thevpc.samples.springnuts.modulea.dal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Employee")
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    private String name;
    private String surname;
    private String phone;
    private String email;
}
