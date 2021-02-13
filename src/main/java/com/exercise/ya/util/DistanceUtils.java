package com.exercise.ya.util;

public class DistanceUtils {
	
	/**
	 * Buenos aires
	 */
	private static final Double LAT = -34.6083;
	private static final Double LNG = -58.3712;
	
	public static Double getBuenosAiresDistance(double lat1, double lon1) {
		if ((lat1 == LAT) && (lon1 == LNG)) {
			return 0d;
		}
		else {
			double theta = lon1 - LNG;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(LAT)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(LAT)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return dist;
		}
	}

}
