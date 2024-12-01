package net.thevpc.samples.springnuts.core.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "AppUserRole")
public class AppUserRoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 40)
  private String name;

  public AppUserRoleEntity() {

  }

  public Long getId() {
    return id;
  }

  public AppUserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public AppUserRoleEntity setName(String name) {
    this.name = name;
    return this;
  }
}