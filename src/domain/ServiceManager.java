package domain;

public class ServiceManager {
    private String name;
    private String identification;
    private String email;
    private String gender;
    private String phone;

    public ServiceManager(String name, String identification, String email, String gender, String phone) {
        this.name = name;
        this.identification = identification;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}