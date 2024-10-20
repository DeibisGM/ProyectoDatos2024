package domain;

public class Zone {
    private String name;
    private double price;
    private int totalSpaces;
    private int availableSpaces;
    private String category;
    private ListaSimple<Asiento> seats;

    public Zone(String name, double price, int totalSpaces, String category) {
        this.name = name;
        this.price = price;
        this.totalSpaces = totalSpaces;
        this.availableSpaces = totalSpaces;
        this.category = category;
        this.seats = new ListaSimple<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 1; i <= totalSpaces; i++) {
            seats.add(new Asiento(i));
        }
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getTotalSpaces() { return totalSpaces; }
    public void setTotalSpaces(int totalSpaces) { this.totalSpaces = totalSpaces; }

    public int getAvailableSpaces() { return availableSpaces; }
    public void setAvailableSpaces(int availableSpaces) { this.availableSpaces = availableSpaces; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public ListaSimple<Asiento> getSeats() { return seats; }
    public void setSeats(ListaSimple<Asiento> seats) { this.seats = seats; }

    public void sellSeat() {
        if (availableSpaces > 0) {
            availableSpaces--;
            // Implement additional logic for selling a seat
        } else {
            // Implement logic for when no seats are available
        }
    }
}