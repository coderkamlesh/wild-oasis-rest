package com.coderkamlesh.wild_oasis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderkamlesh.wild_oasis.entity.Setting;
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

}
