package com.ldubgd.info.models;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class SystemManagers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

//    @OneToMany(mappedBy = "systemManagers")
//    private List<SystemUsers> systemUsers;

//    @OneToMany(mappedBy = "systemManagers")
//    private List<InfoTables> infoTables;
}

