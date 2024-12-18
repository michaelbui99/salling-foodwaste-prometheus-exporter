package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling;

import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos.FoodWaste;

import java.util.List;

public interface SallingFoodWasteApi {
    public List<FoodWaste> getFoodWaste(String zip);
}
