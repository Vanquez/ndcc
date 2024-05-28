package com.kindsonthegenius.fleetmsv2.gg.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.fleetmsv2.es.models.EmployeeType;
import com.kindsonthegenius.fleetmsv2.es.models.JobTitle;
import com.kindsonthegenius.fleetmsv2.es.models.Person;
import com.kindsonthegenius.fleetmsv2.security.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GgMember extends Person {
    @ManyToOne
    @JoinColumn(name="employeetypeid", insertable=false, updatable=false)
    private EmployeeType employeeType;
    private Integer employeetypeid;
    private String photo;
    private String username;

    @ManyToOne
    @JoinColumn(name="jobtitleid", insertable=false, updatable=false)
    private JobTitle jobTitle;
    private Integer jobtitleid;




    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;





    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
}
