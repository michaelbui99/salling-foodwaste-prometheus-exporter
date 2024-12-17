package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos;

import java.util.List;

public class FoodWaste {
    private List<Clearance> clearances;
    private Store store;

    public List<Clearance> getClearances() {
        return clearances;
    }

    public Store getStore() {
        return store;
    }

    public void setClearances(List<Clearance> clearances) {
        this.clearances = clearances;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
