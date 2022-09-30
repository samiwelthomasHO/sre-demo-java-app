package hocto.sredemojavaapp;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class MetricService {

    private MeterRegistry meterRegistry;
    private Counter successful;
    private Counter exceptions;

    public MetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.initCounter();
    }

    private void initCounter() {
        this.meterRegistry.counter("simple.counter", "app", "demo", "team", "sre", "status", "UNKNOWN");
        successful = Counter.builder("simple.counter")
            .tag("app", "demo")
            .tag("team", "sre")
            .tag("status", "SUCCESS")
            .description("A demo counter metric")
            .register(meterRegistry);

        exceptions = Counter.builder("simple.counter")
            .tag("app", "demo")
            .tag("team", "sre")
            .tag("status", "EXCEPTION")
            .description("A demo counter metric")
            .register(meterRegistry);
    }

    public void incrementCounter() {
        Random rand = new Random();
        double randomNumber = rand.nextDouble();

        if (randomNumber <= 0.1) {
            exceptions.increment();
        } else {
            successful.increment();
        }
    }

    public double getCounterValue() {
        return successful.count();
    }

    public double getExceptionsValue() {
        return exceptions.count();
    }

    public double getTotalValue() {
        return successful.count() + exceptions.count();
    }
}