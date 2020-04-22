package info.cheremisin.recipeapp.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RecipeCounterMetrics {

    private MeterRegistry meterRegistry;
    private Counter recipesShown;

    public RecipeCounterMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    private void initOrderCounters() {
        //recipesShown = meterRegistry.counter("recipes.shown", "type", "mexican"); // 1 - create a counter
        recipesShown = Counter.builder("recipes.shown")    // 2 - create a counter using the fluent API
                .tag("type", "mexican")
                .description("The number of recipes shown")
                .register(meterRegistry);
    }

    public void incrementShown(){
        recipesShown.increment();
    }
}