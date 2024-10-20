package domain;

import java.time.LocalTime;

// Clase para servicios adicionales
public class AdditionalService {
    private String companyName;
    private LocalTime startTime;
    private LocalTime endTime;
    private int contractCount;
    private double price;
    private ServiceManager manager;

    public AdditionalService(String companyName, LocalTime startTime,
                             LocalTime endTime, int contractCount, double price) {
        this.companyName = companyName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.contractCount = contractCount;
        this.price = price;
    }

    // Getters y setters
}