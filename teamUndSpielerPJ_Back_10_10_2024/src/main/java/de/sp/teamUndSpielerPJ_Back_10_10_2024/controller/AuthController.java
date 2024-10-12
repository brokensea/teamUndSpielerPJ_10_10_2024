package de.sp.teamUndSpielerPJ_Back_10_10_2024.controller;


import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.auth.AuthDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.auth.JwtDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.AuthentificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    AuthentificationService authentificationService;

    public AuthController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signin(Authentication authentication) {
        return ResponseEntity.ok(new JwtDto(authentificationService.getJwt(authentication)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthDto dto) {
        try {
            User user = authentificationService.signUp(dto);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   /* @PostMapping("/signup")
    public User signup(@RequestBody AuthDto dto) {
        return authentificationService.signUp(dto);
    }*/

   /* @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }*/
}