package app.model;

import java.io.Serializable;
import java.util.List;

public class Passenger {
    private final String category;
    private final List<String> bags;

    public Passenger(String category, List<String> bags) {
        this.category = category;
        this.bags = bags;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getBags() {
        return bags;
    }

}
