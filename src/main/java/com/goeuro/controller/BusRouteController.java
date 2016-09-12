package main.java.com.goeuro.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.goeuro.representation.Response;
import main.java.com.goeuro.services.BusRouteService;

@RestController
public class BusRouteController {

	/**
	 * logger for logging
	 */
	final static Logger logger = Logger.getLogger(BusRouteController.class);

	private static BusRouteService busRouteService = new BusRouteService();

	/**
	 * @param departureStationId
	 * @param arrivalStationId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/direct")
	public Response handel(@RequestParam(value = "dep_sid") int departureStationId,
			@RequestParam(value = "arr_sid") int arrivalStationId) {
		logger.info("BusRouteService called with parameter dep_sid = " + departureStationId + " and arr_sid = "
				+ arrivalStationId);
		return busRouteService.isThereDirectRoute(departureStationId, arrivalStationId);
	}

	/**
	 * @param args
	 */
	public BusRouteController(ApplicationArguments args) {
		String[] argsArray = args.getSourceArgs();
		if (argsArray != null && argsArray.length > 0)
			busRouteService.initBusRoute(argsArray[0]);
	}
}