package main.controller;

import main.security.UserSecurity;
import main.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityRepository securityRepository;

    @GetMapping
    public List<UserSecurity> getAllSecurity() {
        return securityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSecurity> getSecurityById(@PathVariable Long id) {
        Optional<UserSecurity> security = securityRepository.findById(id);
        return security.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserSecurity createSecurity(@RequestBody UserSecurity security) {
        return securityRepository.save(security);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSecurity> updateSecurity(@PathVariable Long id, @RequestBody UserSecurity securityDetails) {
        return securityRepository.findById(id)
                .map(security -> {
                    security.setLogin(securityDetails.getLogin());
                    security.setPassword(securityDetails.getPassword());
                    security.setRole(securityDetails.getRole());
                    return ResponseEntity.ok(securityRepository.save(security));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecurity(@PathVariable Long id) {
        if (securityRepository.existsById(id)) {
            securityRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
