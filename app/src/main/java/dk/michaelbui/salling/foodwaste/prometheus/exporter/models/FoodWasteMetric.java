package dk.michaelbui.salling.foodwaste.prometheus.exporter.models;

import dk.michaelbui.salling.foodwaste.prometheus.exporter.config.ExporterConfig;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.SallingFoodWasteApiClient;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos.Clearance;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos.FoodWaste;
import io.prometheus.metrics.core.metrics.Gauge;
import io.prometheus.metrics.core.metrics.GaugeWithCallback;

import java.util.List;

public class FoodWasteMetric {
    private static GaugeWithCallback GAUGE;
    private ExporterConfig config;

    public FoodWasteMetric(SallingFoodWasteApiClient foodWasteApiClient, ExporterConfig config) {
        GaugeWithCallback foodWasteGauge = GaugeWithCallback.builder()
                .name("salling_group_food_waste")
                .help("Salling Group Food Waste Metric")
                .labelNames("offer_currency", "offer_discount", "offer_ean",
                        "offer_end_time", "offer_last_update", "offer_original_price",
                        "offer_percent_discount", "offer_start_time", "offer_stock", "offer_stock_unit",
                        "product_description", "product_ean",
                        "store_address_city", "store_address_country", "store_address_street", "store_address_zip",
                        "store_brand", "store_id", "store_name"
                ).callback(callback -> {

                }).register();
    }

    public static void init(SallingFoodWasteApiClient foodWasteApiClient, ExporterConfig config) {
        GAUGE = GaugeWithCallback.builder()
                .name("salling_group_food_waste")
                .help("Salling Group Food Waste Metric")
                .labelNames("offer_currency", "offer_discount", "offer_ean",
                        "offer_end_time", "offer_last_update", "offer_original_price",
                        "offer_percent_discount", "offer_start_time", "offer_stock", "offer_stock_unit",
                        "product_description", "product_ean",
                        "store_address_city", "store_address_country", "store_address_street", "store_address_zip",
                        "store_brand", "store_id", "store_name"
                ).callback(callback -> {
                    List<List<FoodWaste>> foodWastesInZipCodes =
                            config.getZipCodes()
                                    .stream()
                                    .map(foodWasteApiClient::getFoodWaste)
                                    .toList();
                    for (List<FoodWaste> foodWastesInZipCode : foodWastesInZipCodes) {
                        for (FoodWaste foodWaste : foodWastesInZipCode) {
                            for (Clearance clearance : foodWaste.getClearances()) {
                                callback.call(clearance.getOffer().getNewPrice(),
                                        clearance.getOffer().getCurrency(), String.valueOf(clearance.getOffer().getDiscount()), clearance.getOffer().getEan(),
                                        clearance.getOffer().getEndTime(), clearance.getOffer().getLastUpdate(), String.valueOf(clearance.getOffer().getOriginalPrice()),
                                        String.valueOf(clearance.getOffer().getPercentDiscount()), clearance.getOffer().getStartTime(), String.valueOf(clearance.getOffer().getStock()), clearance.getOffer().getStockUnit(),
                                        clearance.getProduct().getDescription(), clearance.getProduct().getEan(),
                                        foodWaste.getStore().getAddress().getCity(), foodWaste.getStore().getAddress().getCountry(), foodWaste.getStore().getAddress().getStreet(), foodWaste.getStore().getAddress().getZip(),
                                        foodWaste.getStore().getBrand(), foodWaste.getStore().getId(), foodWaste.getStore().getName()
                                );
                            }
                        }
                    }

                }).register();
    }
}
