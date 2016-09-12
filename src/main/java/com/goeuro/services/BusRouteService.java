package main.java.com.goeuro.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import main.java.com.goeuro.representation.Response;

@Service
public class BusRouteService {

	/**
	 * logger for logging
	 */
	final static Logger logger = Logger.getLogger(BusRouteService.class);

	private static List<List<Integer>> busRoutes = null;

	/**
	 * @param departureStationId
	 * @param arrivalStationId
	 * @return
	 */
	public Response isThereDirectRoute(int departureStationId, int arrivalStationId) {
		logger.info("searching for route with departureStationId = " + departureStationId + " and arrivalStationId = "
				+ arrivalStationId);

		boolean isThereDirectBusRoute = false;
		int indexOfDeparture;
		int indexOfArrival;

		// check busRouts if empty
		if (busRoutes != null && !busRoutes.isEmpty()) {
			logger.debug("searching for route in " + busRoutes.size() + " routes");
			for (List<Integer> busRoute : busRoutes) {
				indexOfDeparture = busRoute.indexOf(departureStationId);
				indexOfArrival = busRoute.indexOf(arrivalStationId);

				if (indexOfDeparture != -1 && indexOfArrival != -1 && indexOfDeparture < indexOfArrival) {
					isThereDirectBusRoute = true;
					logger.debug("found route for departureStationId = " + departureStationId
							+ " and arrivalStationId = " + arrivalStationId);
					break;
				}
			}
		} else {
			logger.debug("busRoutes are empty!!");
		}
		// return new Response
		return new Response(departureStationId, arrivalStationId, isThereDirectBusRoute);
	}

	/**
	 * @param filePath
	 */
	public void initBusRoute(String filePath) {
		logger.info("initBusRoute started with file path = " + filePath);
		busRoutes = new ArrayList<>();
		BufferedReader br = null;
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String[] stations;
			List<Integer> stationsList;
			while ((sCurrentLine = br.readLine()) != null) {
				stations = sCurrentLine.split(" ");
				stationsList = new ArrayList<>();
				for (int i = 1; i < stations.length; i++) {
					stationsList.add(Integer.parseInt(stations[i]));
				}
				if (!stationsList.isEmpty()) {
					busRoutes.add(stationsList);
					logger.debug(
							"found " + stationsList.size() + " stations for route number: " + (busRoutes.size() - 1));
				}
			}
			logger.debug("found " + busRoutes.size() + " routes");
		} catch (IOException e) {
			logger.error("IOException occurred", e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				logger.error("IOException occurred", ex);
			}
		}
	}
}