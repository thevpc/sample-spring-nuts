package net.thevpc.samples.springnuts.modulea.model;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String UUID;
    private String name;
    private String surname;
    private String phone;
    private String email;
}
