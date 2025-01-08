package com.coderkamlesh.wild_oasis.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.wild_oasis.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	  @Query("SELECT b FROM Booking b WHERE b.cabin.id = :cabinId AND (b.startDate >= :today OR b.status = 'checked-in')")
	    List<Booking> findBookingsByCabinId(@Param("cabinId") Long cabinId, @Param("today") LocalDateTime today);
	  List<Booking> findByUserId(Long userId);
}
