package domain;

import java.time.LocalDate;

// Clase para el personal del evento
public class Staff {
    private String name;
    private String identification;
    private String email;
    private String position;
    private LocalDate birthDate;
    private String accountNumber;
    private boolean isActive;

    public Staff(String name, String identification, String email, String position, LocalDate birthDate, String accountNumber) {
        this.name = name;
        this.identification = identification;
        this.email = email;
        this.position = position;
        this.birthDate = birthDate;
        this.accountNumber = accountNumber;
        this.isActive = true;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}