package in.faizali.moneymanager.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.faizali.moneymanager.dto.AuthDTO;
import in.faizali.moneymanager.dto.ProfileDTO;
import in.faizali.moneymanager.service.ProfileService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    


    @PostMapping("/register")
    public ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO registeredProfile = profileService.registerProfile(profileDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateProfile(@RequestParam String token) {
        boolean isActivated = profileService.activateProfile(token);
        if (isActivated) {
            return ResponseEntity.ok("Profile activated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation token not found or already used");
        }
    }

@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO) {
    if (!profileService.isAccountActive(authDTO.getEmail())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Map.of("message", "Please activate your account via email"));
    }

    try {
        Map<String, Object> loginData = profileService.authenticateAndGenerate(authDTO);
        return ResponseEntity.ok(loginData);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", "Invalid email or password"));
    }
}




    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getPublicProfile() {
        ProfileDTO profileDTO = profileService.getPublicProfile(null);
        return ResponseEntity.ok(profileDTO);
    }
}