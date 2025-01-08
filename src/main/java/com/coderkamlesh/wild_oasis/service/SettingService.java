package com.coderkamlesh.wild_oasis.service;

import org.springframework.stereotype.Service;

import com.coderkamlesh.wild_oasis.Repository.SettingRepository;
import com.coderkamlesh.wild_oasis.entity.Setting;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingService {

	private final SettingRepository settingRepository;
	
	public Setting getSettings() {
       
        return settingRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Settings not found"));
    }
}
