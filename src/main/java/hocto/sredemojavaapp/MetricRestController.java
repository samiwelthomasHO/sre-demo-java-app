package hocto.sredemojavaapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricRestController {

	private MetricService metricService;

	public MetricRestController(MetricService metricService) {
		this.metricService = metricService;
	}

	@GetMapping("/")
	public String index() {
		this.metricService.incrementCounter();
		return "The counter was incremented: " + this.metricService.getCounterValue();
	}

}