package ru.dmitartur.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int id, String role) {
        this.id = (long) id;
        this.role = role;
    }

    public Role(String role, List<User> user) {
        this.role = role;
        this.user = user;
    }

    public Role(int id) {
        this.id = (long) id;
    }

    public Role(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}