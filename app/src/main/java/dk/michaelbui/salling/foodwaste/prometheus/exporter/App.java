package dk.michaelbui.salling.foodwaste.prometheus.exporter;

import io.prometheus.metrics.core.metrics.Gauge;
import io.prometheus.metrics.exporter.httpserver.HTTPServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = LogManager.getLogger();

        HTTPServer server = HTTPServer.builder()
                .port(8080)
                .buildAndStart();


        Gauge discounts = Gauge.builder()
                .name("foodwaste_offcer")
                .help("Current foodwaste offer")
                .labelNames("product", "store")
                .register();

        discounts.labelValues("test", "åbyhøj").set(200);

        logger.info("HTTP Server exposing endpoint /metrics on port {}", server.getPort());
        Thread.currentThread().join();
    }
}
