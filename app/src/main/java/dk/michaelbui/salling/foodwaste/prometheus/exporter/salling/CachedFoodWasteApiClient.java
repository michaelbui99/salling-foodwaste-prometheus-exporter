package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling;

import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos.FoodWaste;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedFoodWasteApiClient implements SallingFoodWasteApi {
    private static final Logger LOGGER = LogManager.getLogger(CachedFoodWasteApiClient.class);
    private final SallingFoodWasteApiClient delegate;
    private final long invaldiationThresholdInMinutes;
    private final Map<String, List<FoodWaste>> cache;
    private final Map<String, OffsetDateTime> lastRequestMap;

    public CachedFoodWasteApiClient(SallingFoodWasteApiClient delegate, long invalidationThresholdInMinutes) {
        this.delegate = delegate;
        this.invaldiationThresholdInMinutes = invalidationThresholdInMinutes;
        this.cache = new HashMap<>();
        this.lastRequestMap = new HashMap<>();
    }

    public CachedFoodWasteApiClient(SallingFoodWasteApiClient delegate) {
        this(delegate, 5);
    }

    @Override
    public List<FoodWaste> getFoodWaste(String zip) {
        if (!this.cache.containsKey(zip)){
            LOGGER.info("Cache miss");
            return fetchAndUpdateCache(zip);
        }

        if (!this.lastRequestMap.containsKey(zip)){
            throw new IllegalStateException("cache has not been maintained properly");
        }

        OffsetDateTime lastRequest = lastRequestMap.get(zip);
        OffsetDateTime now = OffsetDateTime.now();
        long minutesBetweenRequests = ChronoUnit.MINUTES.between(lastRequest, now);
        if (minutesBetweenRequests >= invaldiationThresholdInMinutes){
            LOGGER.info("Invalidating Cache");
            return fetchAndUpdateCache(zip);
        }

        return this.cache.get(zip);
    }

    private List<FoodWaste> fetchAndUpdateCache(String zip){
        OffsetDateTime now = OffsetDateTime.now();

        List<FoodWaste> foodWastes = delegate.getFoodWaste(zip);
        this.lastRequestMap.put(zip, now);
        this.cache.put(zip, foodWastes);

        return foodWastes;
    }
}
