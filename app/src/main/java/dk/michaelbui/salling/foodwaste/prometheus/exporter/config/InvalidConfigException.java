package dk.michaelbui.salling.foodwaste.prometheus.exporter.config;

public class InvalidConfigException extends RuntimeException {

    public InvalidConfigException(String reason) {
        super("Invalid exporter configuration: " + reason);
    }
}
