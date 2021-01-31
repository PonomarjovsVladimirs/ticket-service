package app.model;

public class Ticket {
    private final String category;
    private final String price;

    public Ticket(String category, String price) {
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }
}
