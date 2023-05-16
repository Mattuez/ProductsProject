package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UsedProduct extends Product{
    private LocalDate date;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public UsedProduct() {
    }

    public UsedProduct(String name, Double price, LocalDate date) {
        super(name, price);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String priceTag() {
        return name + " (used) $ " + String.format(Locale.US, "%.2f", price) +
                " (Manufacture date: " + date.format(DATE_FORMAT) + ")";
    }
}
