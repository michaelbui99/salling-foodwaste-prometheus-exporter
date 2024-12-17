package dk.michaelbui.salling.foodwaste.prometheus.exporter;

import com.google.gson.Gson;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.config.ExporterConfig;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.models.FoodWasteClearanceMetric;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.os.EnvironmentVariableReader;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.os.EnvironmentVariableReaderImpl;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.os.EnvironmentVariables;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.SallingFoodWasteApiClient;
import io.prometheus.metrics.exporter.httpserver.HTTPServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = LogManager.getLogger(App.class);

        ExporterConfig config = getConfig();
        SallingFoodWasteApiClient apiClient = new SallingFoodWasteApiClient(config.getFoodWasteApiURL(), config.getFoodWasteApiKey());
        FoodWasteClearanceMetric.init(apiClient, config);

        HTTPServer server = HTTPServer.builder()
                .port(config.getPort())
                .buildAndStart();

        logger.info("Exposing food waste metrics on /metrics on port {}", server.getPort());
        Thread.currentThread().join();
    }

    private static ExporterConfig getConfig() {
        EnvironmentVariableReader environmentVariableReader = new EnvironmentVariableReaderImpl();
        Gson gson = new Gson();

        return new ExporterConfig(
                environmentVariableReader.read(EnvironmentVariables.FW_EXPORTER_API_URL, "https://api.sallinggroup.com/v1/food-waste"),
                environmentVariableReader.read(EnvironmentVariables.FW_EXPORTER_API_KEY, ""),
                gson.fromJson(environmentVariableReader.read(EnvironmentVariables.FW_EXPORTER_ZIP_CODES, "[]"), List.class),
                environmentVariableReader.read(EnvironmentVariables.FW_EXPORTER_PORT, "8080")
        );
    }
}
