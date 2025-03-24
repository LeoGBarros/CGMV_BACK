package com.example.API_CGMV.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PasswordValidatorController {

    @PostMapping("/validate-password")
    public ResponseEntity<PasswordValidationResponse> validatePassword(@RequestBody String password) {
        try {
            password = password.trim();

            List<String> errors = validatePasswordErrors(password);

            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new PasswordValidationResponse(false, errors));
            }

            return ResponseEntity.ok(new PasswordValidationResponse(true, List.of("Senha válida.")));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PasswordValidationResponse(false, List.of("Erro interno ao validar a senha.")));
        }
    }

    private List<String> validatePasswordErrors(String password) {
        List<String> errors = new ArrayList<>();

        if (password.length() < 9) errors.add("A senha deve ter pelo menos 9 caracteres.");

        if (!password.matches(".*\\d.*")) errors.add("A senha deve conter pelo menos 1 dígito.");
        if (!password.matches(".*[a-z].*")) errors.add("A senha deve conter pelo menos 1 letra minúscula.");
        if (!password.matches(".*[A-Z].*")) errors.add("A senha deve conter pelo menos 1 letra maiúscula.");
        if (!password.matches(".*[!@#$%^&*()\\-+].*")) errors.add("A senha deve conter pelo menos 1 caractere especial (!@#$%^&*()-+).");

        Set<Character> uniqueChars = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!uniqueChars.add(c)) {
                errors.add("A senha não pode conter caracteres repetidos.");
                break;
            }
        }

        return errors;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PasswordValidationResponse> handleGlobalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new PasswordValidationResponse(false, List.of("Erro inesperado no servidor.")));
    }

}
class PasswordValidationResponse {
    private boolean isValid;
    private List<String> messages;

    public PasswordValidationResponse(boolean isValid, List<String> messages) {
        this.isValid = isValid;
        this.messages = messages;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getMessages() {
        return messages;
    }
}
