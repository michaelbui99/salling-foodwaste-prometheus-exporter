package dk.michaelbui.salling.foodwaste.prometheus.exporter.salling;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import dk.michaelbui.salling.foodwaste.prometheus.exporter.salling.dtos.FoodWaste;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SallingFoodWasteApiClient {
    private String url = "";
    private String apiKey = "";
    private final Gson gson = new Gson();

    public SallingFoodWasteApiClient(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }


    public List<FoodWaste> getFoodWaste(String zip) {
        String parametrizedUrl = String.format("%s?zip=%s", this.url, zip);

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(parametrizedUrl))
                    .header("Authorization", String.format("Bearer %s", this.apiKey))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Type foodWasteListType = new TypeToken<List<FoodWaste>>() {
            }.getType();

            return gson.fromJson(response.body(), foodWasteListType);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
