package com.depaul.depaulmarketplace.user;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private Long id;
    private String roleName;


    public UserRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UserRole() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
