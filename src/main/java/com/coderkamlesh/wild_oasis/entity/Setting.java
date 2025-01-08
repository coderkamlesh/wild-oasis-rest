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

@Entity
@Table(name = "settings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_booking_length", nullable = false)
    private Integer minBookingLength; 

    @Column(name = "max_booking_length", nullable = false)
    private Integer maxBookingLength;

    @Column(name = "max_guests_per_booking", nullable = false)
    private Integer maxGuestsPerBooking; 

    @Column(name = "breakfast_price", nullable = false, columnDefinition = "DOUBLE")
    private Double breakfastPrice; 
}
