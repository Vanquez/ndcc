package com.kindsonthegenius.fleetmsv2.security.models;

import com.kindsonthegenius.fleetmsv2.es.models.Employee;
import com.kindsonthegenius.fleetmsv2.es.models.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer Id;
    public String firstname;
    public String lastname;
    public String username;
    public String password;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    Set<Role> roles = new HashSet<>();






    @ManyToOne
    @JoinColumn(name="jobtitleid", insertable=false, updatable=false)
    private JobTitle jobTitle;
    private Integer jobtitleid;




}
