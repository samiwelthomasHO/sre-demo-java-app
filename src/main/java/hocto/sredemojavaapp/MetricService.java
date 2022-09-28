package hocto.sredemojavaapp;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MetricService {

    private MeterRegistry meterRegistry;
    private Counter counter;

    public MetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.initCounter();
    }

    private void initCounter() {
        this.meterRegistry.counter("simple.counter", "app", "demo", "team", "sre");
        counter = Counter.builder("simple.counter")
            .tag("app", "demo")
            .tag("team", "sre")
            .description("A demo counter metric")
            .register(meterRegistry);
    }

    public void incrementCounter() {
        counter.increment();
    }

    public double getCounterValue() {
        return counter.count();
    }
}