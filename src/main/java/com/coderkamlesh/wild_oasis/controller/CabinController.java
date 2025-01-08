package com.coderkamlesh.wild_oasis.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coderkamlesh.wild_oasis.entity.Cabin;
import com.coderkamlesh.wild_oasis.service.CabinService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cabins")
public class CabinController {

	private final CabinService cabinService;

	@PostMapping()
	public ResponseEntity<?> addCabin(@RequestParam("name") String name, @RequestParam("maxCapacity") Integer capacity,
			@RequestParam("regularPrice") Double price, @RequestParam("discount") Integer discountRate,
			@RequestParam("description") String details, @RequestParam("image") MultipartFile image) {
		try {

			Cabin cabin = new Cabin();
			cabin.setName(name);
			cabin.setMaxCapacity(capacity);
			cabin.setRegularPrice(price);
			cabin.setDiscount(discountRate);
			cabin.setDescription(details);

			Cabin savedCabin = cabinService.addCabin(cabin, image);
			return ResponseEntity.ok(savedCabin);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error in adding cabin");
		}
	}

	@GetMapping
	public ResponseEntity<List<Cabin>> allCabins() {
		List<Cabin> allCabinsList = cabinService.getAllCabin();
		return ResponseEntity.ok(allCabinsList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cabin> singleCabins(@PathVariable Long id) {
		Cabin cabin = cabinService.getSingleCabin(id);
		return ResponseEntity.ok(cabin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCabin(@PathVariable("id") Long cabinId, @RequestParam("name") String name,
			@RequestParam("maxCapacity") Integer capacity, @RequestParam("regularPrice") Double price,
			@RequestParam("discount") Integer discountRate, @RequestParam("description") String details,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		try {
			
			Cabin updatedCabin = cabinService.updateCabin(cabinId, name, capacity, price, discountRate, details, image);
			return ResponseEntity.ok(updatedCabin);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error in updating cabin");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
