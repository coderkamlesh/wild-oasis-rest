package com.coderkamlesh.wild_oasis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import lombok.Value;

@RestController
@RequestMapping("/api/auth/public/google")
public class GoogleAuthController {

	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String clientSecret;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/callback")
	public ResponseEntity<?> handleGoogleCallback(@RequestParam String code){
		try {
			//1. Exchnage auth code for tokens
			String tokenEndPoint="https://oauth2.googleapis.com/token";
			
			Map<String,String> params=new HashMap<>();
			params.put("code", code);
			params.put("client_id", clientId);
			params.put("client_secret", clientSecret);
			params.put("redirect_uri", "https://developers.google.com/oauthplayground");
			params.put("grant_type", "authorization_code");
			
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			HttpEntity<Map<String, String>> request=new HttpEntity<>(params,headers);
			
//			ResponseEntity<Map> tokenResponse= restTemplate.postForEntity("https://oauth2.googleapis.com/token", request, Map.class);
//			String idToken=(String) tokenResponse.getBody().get("id_token");
			return ResponseEntity.ok("");
		} catch (Exception e) {
			return ResponseEntity.ok("");
		}
	}
}
