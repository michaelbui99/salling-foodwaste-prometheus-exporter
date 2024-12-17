package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos;

import java.util.Map;

public class Product {
    private Map<String, String> categories;
    private String description;
    private String ean;

    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
