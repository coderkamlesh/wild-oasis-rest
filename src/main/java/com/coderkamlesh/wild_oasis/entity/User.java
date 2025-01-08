package com.coderkamlesh.wild_oasis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
  
    private String fullName;  
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    private String password;

    @Column(name = "national_id", unique = true)
    private String nationalId; 

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "country_flag")
    private String countryFlag; 
    

}
