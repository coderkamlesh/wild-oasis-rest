package com.coderkamlesh.wild_oasis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderkamlesh.wild_oasis.entity.Setting;
import com.coderkamlesh.wild_oasis.service.SettingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settings")
public class SettingController {

	private final SettingService settingService;
	
	@GetMapping
	public ResponseEntity<Setting> getSettings() {
        Setting settings = settingService.getSettings();
        return ResponseEntity.ok(settings);
    }
	
}
