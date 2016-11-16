package main.models

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = "users")
@ToString(includeNames = true)
public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  

  @Column(name = "name")
  private String name;
  

  @Column(name = "pass")
  private String pass;



  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  //@JsonIgnore
  private Set<Role> roles = new HashSet<Role>();
  
  public User() { }


  // Getter and setter methods

  long getId() {
    return id
  }

  void setId(long id) {
    this.id = id
  }

  String getName() {
    return name
  }

  void setName(String name) {
    this.name = name
  }

  String getPass() {
    return pass
  }

  void setPass(String pass) {
    this.pass = pass
  }

  Set<Role> getRoles() {
    return roles
  }

  void setRoles(Set<Role> roles) {
    this.roles = roles
  }
} // class User
