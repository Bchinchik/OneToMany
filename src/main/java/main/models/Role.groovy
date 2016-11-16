package main.models

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = "roles")
@ToString(includeNames = true)
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "roleName")
    private String roleName;


   // @Column(name = "amount")
   // private Long amount;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    Role() { }

    // Getter and setter methods
    long getId() {
        return id
    }

    void setId(long id) {
        this.id = id
    }

    String getRoleName() {
        return roleName
    }

    void setRoleName(String roleName) {
        this.roleName = roleName
    }


    Long getUser() {
        return user.id
    }

    void setUser(User user) {
        this.user = user
    }
} // class Role
