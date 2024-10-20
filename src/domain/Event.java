package domain;

import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event {
    private String name;
    private String eventType;
    private String details;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private int maxAttendees;
    private ObservableList<Zone> zones;
    private boolean isActive;

    public Event(String name, String eventType, String details, LocalDate date,
                 String time, String location, int maxAttendees, ObservableList<Zone> zones) {
        this.name = name;
        this.eventType = eventType;
        this.details = details;
        this.date = date;
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.zones = zones;
        this.isActive = true;
    }

    // Getters
    public String getName() { return name; }
    public String getType() { return eventType; }
    public String getDetails() { return details; }
    public LocalDate getDate() { return date; }
    public String getTime() { return time.format(DateTimeFormatter.ofPattern("HH:mm")); }
    public String getPlace() { return location; }
    public int getCapacity() { return maxAttendees; }
    public ObservableList<Zone> getZones() { return zones; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setDetails(String details) { this.details = details; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setLocation(String location) { this.location = location; }
    public void setMaxAttendees(int maxAttendees) { this.maxAttendees = maxAttendees; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getEventDateTime() {
        return LocalDateTime.of(date, time);
    }

    public boolean isEventPassed() {
        return LocalDateTime.now().isAfter(getEventDateTime());
    }

    public long getMonthsUntilEvent() {
        return ChronoUnit.MONTHS.between(LocalDateTime.now(), getEventDateTime());
    }
}