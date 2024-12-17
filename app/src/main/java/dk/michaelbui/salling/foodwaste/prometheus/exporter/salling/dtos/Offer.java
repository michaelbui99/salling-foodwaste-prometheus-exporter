package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos;

public class Offer {
    private String currency;
    private float discount;
    private String ean;
    private String endTime;
    private String lastUpdate;
    private String startTime;
    private double newPrice;
    private double originalPrice;
    private float percentDiscount;
    private int stock;
    private String stockUnit;

    public String getCurrency() {
        return currency;
    }

    public float getDiscount() {
        return discount;
    }

    public String getEan() {
        return ean;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getStartTime() {
        return startTime;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public float getPercentDiscount() {
        return percentDiscount;
    }

    public int getStock() {
        return stock;
    }

    public String getStockUnit() {
        return stockUnit;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPercentDiscount(float percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit;
    }
}
