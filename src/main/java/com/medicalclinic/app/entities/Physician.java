package com.medicalclinic.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Physicians")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Physician {

    @Id
    @SequenceGenerator(name = "physician_seq", sequenceName = "physician_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "physician_seq")
    private Long id;

    private String name;
    private String contactNumber;

    private String email;

    @OneToMany(mappedBy = "physician", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Appointment> appointments;
}
