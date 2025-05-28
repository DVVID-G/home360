package com.pragma.home360.domain.model;

import com.pragma.home360.domain.exceptions.*;

import java.time.LocalDate;
import java.time.Period;

public class UserModel {
    private Long userId;
    private String userIdentification;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String role;

    public UserModel(Long userId, String userIdentification, String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String email, String password, String role) {
        if (firstName == null || firstName.isBlank()) {
            throw new RequiredFieldException("El nombre es obligatorio");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new RequiredFieldException("El apellido es obligatorio");
        }
        if (userIdentification == null || userIdentification.isBlank()) {
            throw new RequiredFieldException("El documento es obligatorio");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new RequiredFieldException("El teléfono es obligatorio");
        }
        if (dateOfBirth == null) {
            throw new RequiredFieldException("La fecha de nacimiento es obligatoria");
        }
        if (email == null || email.isBlank()) {
            throw new RequiredFieldException("El correo es obligatorio");
        }
        if (password == null || password.isBlank()) {
            throw new RequiredFieldException("La contraseña es obligatoria");
        }

        // 2. Documento solo digits
        if (!userIdentification.matches("\\d+")) {
            throw new InvalidDocumentException();
        }

        // 3. Teléfono: hasta 13 caracteres, puede empezar con +
        if (!phoneNumber.matches("^\\+?\\d{1,13}$")) {
            throw new InvalidPhoneException();
        }

        // 4. Email válido
        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailException();
        }

        // 5. Mayor de edad
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        if (age < 18) {
            throw new InvalidAgeException();
        }

        if (role == null ||
                !(role.equals("VENDEDOR")
                        || role.equals("ADMINISTRADOR")
                        || role.equals("CLIENTE"))) {
            throw new IllegalArgumentException("Invalid role");
        }

        this.userId = userId;
        this.userIdentification = userIdentification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserIdentification() {
        return userIdentification;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
