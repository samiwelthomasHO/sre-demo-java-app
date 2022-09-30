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
		return String.format("Successful: %s, Exceptions: %s, Total: %s", 
			metricService.getCounterValue(), 
			metricService.getExceptionsValue(), 
			metricService.getTotalValue());
	}

}