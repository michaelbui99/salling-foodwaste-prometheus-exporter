package dk.michaelbui.salling.foodwaste.prometheus.exporter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.os.EnvironmentVariables;

import java.util.ArrayList;
import java.util.List;

public class ExporterConfig {
    private final String foodWasteApiURL;
    private final String foodWasteApiKey;
    private final List<String> zipCodes;
    private final long cacheInvalidationThreshold;
    private final int port;

    public ExporterConfig(String foodWasteApiURL, String foodWasteApiKey, List<String> zipCodes, String port, String cacheInvalidationThreshold) {
        this(foodWasteApiURL, foodWasteApiKey, zipCodes, Integer.parseInt(port), Long.parseLong(cacheInvalidationThreshold));
    }

    public ExporterConfig(String foodWasteApiURL, String foodWasteApiKey, List<String> zipCodes, int port, long cacheInvalidationThreshold) {
        this.foodWasteApiURL = foodWasteApiURL;
        this.foodWasteApiKey = foodWasteApiKey;
        this.zipCodes = zipCodes;
        this.port = port;
        this.cacheInvalidationThreshold = cacheInvalidationThreshold;

        if (foodWasteApiKey == null || foodWasteApiKey.isBlank()) {
            throw new InvalidConfigException(String.format("Missing required Salling Group API key (%s)", EnvironmentVariables.FW_EXPORTER_API_KEY));
        }
    }

    public String getFoodWasteApiURL() {
        return foodWasteApiURL;
    }

    public String getFoodWasteApiKey() {
        return foodWasteApiKey;
    }

    public List<String> getZipCodes() {
        return zipCodes;
    }

    public int getPort() {
        return port;
    }

    public long getCacheInvalidationThreshold() {
        return cacheInvalidationThreshold;
    }
}
