package com.coderkamlesh.wild_oasis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.wild_oasis.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 User findByEmail(String email);

}
