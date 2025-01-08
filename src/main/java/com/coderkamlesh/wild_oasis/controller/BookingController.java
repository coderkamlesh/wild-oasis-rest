package com.coderkamlesh.wild_oasis.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderkamlesh.wild_oasis.dto.Bookingdto;
import com.coderkamlesh.wild_oasis.entity.Booking;
import com.coderkamlesh.wild_oasis.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	@PostMapping
	public ResponseEntity<?> createBooking(@RequestBody Bookingdto bookingDto){
		try {
			Booking booking = bookingService.createBooking( bookingDto);
			return ResponseEntity.ok(booking);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "Booking Create failed", "message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
		}
	}
	
	@GetMapping("/booked-dates/{cabinId}")
	public ResponseEntity<List<LocalDate>> getBookedDates(@PathVariable Long cabinId) {
		List<LocalDate> bookedDates = bookingService.getBookedDatesByCabinId(cabinId);
		return ResponseEntity.ok(bookedDates);
	}

	@GetMapping("user/{userId}")
	public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
		return bookingService.getBookingsByUserId(userId);
	}
	
	@GetMapping("/{id}")
	public Booking getBookingsById(@PathVariable Long id) {
		return bookingService.getBookingById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);

		return ResponseEntity.ok().body("Booking Deleted Successfully");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Bookingdto updatedBooking) {
		try {
			Booking booking = bookingService.updateBooking(id, updatedBooking);
			return ResponseEntity.ok(booking);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "Booking update failed", "message", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
		}
	}

}
