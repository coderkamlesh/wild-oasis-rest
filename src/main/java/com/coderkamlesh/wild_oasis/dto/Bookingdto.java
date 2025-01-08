package com.coderkamlesh.wild_oasis.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bookingdto {
	private Long id;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer numNights;
	private Integer numGuests;
	private Double cabinPrice;
	private Double extrasPrice;
	private Double totalPrice;
	private String status;
	private Boolean hasBreakfast;
	private Boolean isPaid;
	private String observations;
	private Long user_id;
	private Long cabin_id;
}
