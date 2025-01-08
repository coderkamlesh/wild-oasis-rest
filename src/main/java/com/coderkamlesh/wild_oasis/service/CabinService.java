package com.coderkamlesh.wild_oasis.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coderkamlesh.wild_oasis.Repository.CabinRepository;
import com.coderkamlesh.wild_oasis.entity.Cabin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CabinService {

	private final CabinRepository cabinRepository;
	private final CloudinaryService cloudinaryService;

	public Cabin addCabin(Cabin cabin, MultipartFile image) throws IOException {
		try {
			Map<String, Object> imageRes = cloudinaryService.uploadImage(image);
			String imageUrl = (String) imageRes.get("secure_url");
			if (imageUrl == null) {
				throw new IOException("Image upload failed");
			}

			// Set uploaded image URL to cabin
			cabin.setImage(imageUrl);

			return cabinRepository.save(cabin);
		} catch (Exception e) {
			throw new IOException("Image upload failed: " + e.getMessage());
		}
	}

	public List<Cabin> getAllCabin() {

		return cabinRepository.findAll();
	}

	public Cabin getSingleCabin(Long id){
		Cabin cabin= cabinRepository.findById(id).orElseThrow(()-> new RuntimeException("Cabin not found"));
		
		return cabin;
	}
	public Cabin updateCabin(Long cabinId, String name, Integer capacity, Double price, Integer discountRate,
			String details, MultipartFile image) throws IOException {


		Optional<Cabin> existingCabin = cabinRepository.findById(cabinId);
		if (!existingCabin.isPresent()) {
			throw new RuntimeException("Cabin not found");
		}

		Cabin cabin = existingCabin.get();
		cabin.setName(name);
		cabin.setMaxCapacity(capacity);
		cabin.setRegularPrice(price);
		cabin.setDiscount(discountRate);
		cabin.setDescription(details);


		if (image != null && !image.isEmpty()) {
			Map<String, Object> imageRes = cloudinaryService.uploadImage(image);
			String imageUrl = (String) imageRes.get("secure_url");
			if (imageUrl == null) {
				throw new IOException("Image upload failed");
			}
			cabin.setImage(imageUrl); 
		}


		return cabinRepository.save(cabin);
	}

}
