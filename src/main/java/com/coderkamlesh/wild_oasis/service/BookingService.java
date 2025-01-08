package com.coderkamlesh.wild_oasis.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coderkamlesh.wild_oasis.Repository.BookingRepository;
import com.coderkamlesh.wild_oasis.Repository.CabinRepository;
import com.coderkamlesh.wild_oasis.Repository.UserRepository;
import com.coderkamlesh.wild_oasis.dto.Bookingdto;
import com.coderkamlesh.wild_oasis.entity.Booking;
import com.coderkamlesh.wild_oasis.entity.Cabin;
import com.coderkamlesh.wild_oasis.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;
	private final CabinRepository cabinRepository;
	private final UserRepository userRepository;

	public List<LocalDate> getBookedDatesByCabinId(Long cabinId) {
		LocalDateTime today = LocalDate.now().atStartOfDay();
		List<Booking> bookings = bookingRepository.findBookingsByCabinId(cabinId, today);

		if (bookings.isEmpty()) {
			return Collections.emptyList();
		}

		return bookings.stream().flatMap(booking -> {
			List<LocalDate> dates = new ArrayList<>();
			LocalDate startDate = booking.getStartDate().toLocalDate();
			LocalDate endDate = booking.getEndDate().toLocalDate();
			long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
			for (long i = 0; i <= daysBetween; i++) {
				dates.add(startDate.plusDays(i));
			}
			return dates.stream();
		}).collect(Collectors.toList());
	}

	public List<Booking> getBookingsByUserId(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

	public void deleteBooking(Long id) {

		if (!bookingRepository.existsById(id)) {
			throw new IllegalArgumentException("Booking with ID " + id + " does not exist.");
		}

		bookingRepository.deleteById(id);
	}

	public Booking updateBooking(Long id, Bookingdto updatedBooking) {

		Booking existingBooking = bookingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Booking with ID " + id + " does not exist."));

		if (updatedBooking.getStartDate() != null) {
			existingBooking.setStartDate(updatedBooking.getStartDate());
		}
		if (updatedBooking.getEndDate() != null) {
			existingBooking.setEndDate(updatedBooking.getEndDate());
		}
		if (updatedBooking.getNumNights() != null) {
			existingBooking.setNumNights(updatedBooking.getNumNights());
		}
		if (updatedBooking.getNumGuests() != null) {
			existingBooking.setNumGuests(updatedBooking.getNumGuests());
		}
		if (updatedBooking.getCabinPrice() != null) {
			existingBooking.setCabinPrice(updatedBooking.getCabinPrice());
		}
		if (updatedBooking.getExtrasPrice() != null) {
			existingBooking.setExtrasPrice(updatedBooking.getExtrasPrice());
		}
		if (updatedBooking.getTotalPrice() != null) {
			existingBooking.setTotalPrice(updatedBooking.getTotalPrice());
		}
		if (updatedBooking.getStatus() != null) {
			existingBooking.setStatus(updatedBooking.getStatus());
		}
		if (updatedBooking.getHasBreakfast() != null) {
			existingBooking.setHasBreakfast(updatedBooking.getHasBreakfast());
		}
		if (updatedBooking.getIsPaid() != null) {
			existingBooking.setIsPaid(updatedBooking.getIsPaid());
		}
		if (updatedBooking.getObservations() != null) {
			existingBooking.setObservations(updatedBooking.getObservations());
		}
		if (updatedBooking.getUser_id() != null) {
			User user = userRepository.findById(updatedBooking.getUser_id())
					.orElseThrow(() -> new IllegalArgumentException(
							"User with ID " + updatedBooking.getUser_id() + " does not exist."));
			existingBooking.setUser(user);
		}
		if (updatedBooking.getCabin_id() != null) {
			Cabin cabin = cabinRepository.findById(updatedBooking.getCabin_id())
					.orElseThrow(() -> new IllegalArgumentException(
							"Cabin with ID " + updatedBooking.getCabin_id() + " does not exist."));
			existingBooking.setCabin(cabin);
		}

		return bookingRepository.save(existingBooking);
	}

	public Booking createBooking(Bookingdto newBooking) {

	    Booking booking = new Booking();

	    // Set startDate
	    if (newBooking.getStartDate() != null) {
	        booking.setStartDate(newBooking.getStartDate());
	    } else {
	        throw new IllegalArgumentException("Start date is required.");
	    }

	    // Set endDate
	    if (newBooking.getEndDate() != null) {
	        booking.setEndDate(newBooking.getEndDate());
	    } else {
	        throw new IllegalArgumentException("End date is required.");
	    }

	    // Set other fields
	    if (newBooking.getNumNights() != null) {
	        booking.setNumNights(newBooking.getNumNights());
	    }

	    if (newBooking.getNumGuests() != null) {
	        booking.setNumGuests(newBooking.getNumGuests());
	    }

	    if (newBooking.getCabinPrice() != null) {
	        booking.setCabinPrice(newBooking.getCabinPrice());
	    }

	    if (newBooking.getExtrasPrice() != null) {
	        booking.setExtrasPrice(newBooking.getExtrasPrice());
	    }

	    if (newBooking.getTotalPrice() != null) {
	        booking.setTotalPrice(newBooking.getTotalPrice());
	    }

	    if (newBooking.getStatus() != null) {
	        booking.setStatus(newBooking.getStatus());
	    }

	    if (newBooking.getHasBreakfast() != null) {
	        booking.setHasBreakfast(newBooking.getHasBreakfast());
	    }

	    if (newBooking.getIsPaid() != null) {
	        booking.setIsPaid(newBooking.getIsPaid());
	    }

	    if (newBooking.getObservations() != null) {
	        booking.setObservations(newBooking.getObservations());
	    }

	    // Set user based on user_id
	    if (newBooking.getUser_id() != null) {
	        User user = userRepository.findById(newBooking.getUser_id())
	                .orElseThrow(() -> new IllegalArgumentException(
	                        "User with ID " + newBooking.getUser_id() + " does not exist."));
	        booking.setUser(user);
	    } else {
	        throw new IllegalArgumentException("User ID is required.");
	    }

	    // Set cabin based on cabin_id
	    if (newBooking.getCabin_id() != null) {
	        Cabin cabin = cabinRepository.findById(newBooking.getCabin_id())
	                .orElseThrow(() -> new IllegalArgumentException(
	                        "Cabin with ID " + newBooking.getCabin_id() + " does not exist."));
	        booking.setCabin(cabin);
	    } else {
	        throw new IllegalArgumentException("Cabin ID is required.");
	    }

	    // Save and return the booking entity
	    return bookingRepository.save(booking);
	}

	public Booking getBookingById(Long id) {
		// TODO Auto-generated method stub
		return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("booking does not exist"));
	}

}
