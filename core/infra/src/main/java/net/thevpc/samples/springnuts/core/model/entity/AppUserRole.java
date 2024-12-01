package net.thevpc.samples.springnuts.core.model.entity;


public class AppUserRole {
  private Long id;

  private String name;

  public AppUserRole() {

  }

  public Long getId() {
    return id;
  }

  public AppUserRole setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public AppUserRole setName(String name) {
    this.name = name;
    return this;
  }
}