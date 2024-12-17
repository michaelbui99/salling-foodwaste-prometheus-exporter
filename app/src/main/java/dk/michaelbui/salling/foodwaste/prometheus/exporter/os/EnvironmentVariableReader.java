package dk.michaelbui.salling.foodwaste.prometheus.exporter.os;

public interface EnvironmentVariableReader {
    public String read(String environmentVariable, String defaultValue);
}
