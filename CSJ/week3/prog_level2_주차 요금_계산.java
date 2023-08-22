package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    static Map<String, Integer> noCalculatedFeeMap = new HashMap<>();
	static ArrayList<CarFee> calculatedFees = new ArrayList<>();
	static Map<Integer, Integer> result = new LinkedHashMap<>();
	static final int LAST_PARKING_TIME_TO_MINUTE = formatCheckedTimeToMinute(new String[]{"23","59"});

	public static int[] solution(int[] fees, String[] records) {
		int[] answer;

		for (String record : records) {
			String[] recordData = record.split(" ");
			String[] times = recordData[0].split(":");

			int checkedTimeToMinute = formatCheckedTimeToMinute(times);

			if (recordData[2].equals("OUT")) {
				int checkedTimeWhenIn = noCalculatedFeeMap.get(recordData[1]);
				calculatedFees.add(new CarFee(recordData[1], (checkedTimeToMinute - checkedTimeWhenIn)));
				noCalculatedFeeMap.remove(recordData[1]);
			} else {
				noCalculatedFeeMap.put(recordData[1], checkedTimeToMinute);
			}
		}

		for(String carNumber : noCalculatedFeeMap.keySet()) {
			Integer checkedCarParkingTime = noCalculatedFeeMap.get(carNumber);
			calculatedFees.add(new CarFee(carNumber,LAST_PARKING_TIME_TO_MINUTE - checkedCarParkingTime));
		}
		Collections.sort(calculatedFees);

		for (CarFee carFee : calculatedFees) {
			if(result.containsKey(carFee.intTypeCarNumber)) {
				result.replace(carFee.intTypeCarNumber, result.get(carFee.intTypeCarNumber) + carFee.time);
				continue;
			}
			result.put(carFee.intTypeCarNumber, carFee.time);
		}

		answer = new int[result.size()];

		int i = 0;
		for (Integer intTypeCarNumber : result.keySet()) {
			answer[i++] = calcFee(result.get(intTypeCarNumber),fees);
		}

		return answer;
	}

	private static int formatCheckedTimeToMinute(String[] times) {
		return ((times[0].charAt(0) * 10) + times[0].charAt(1)) * 60 + Integer.parseInt(times[1]);
	}

	static int calcFee(int time, int[] fees) {
		if (time <= fees[0]) {
			return fees[1];
		}
		System.out.println((int)(fees[1] + Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3]));
		return (int)(fees[1] + Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3]);
	}

	static class CarFee implements Comparable<CarFee> {
		String carNumber;
		int time;
		int intTypeCarNumber;

		public CarFee(String carNumber, int time) {
			this.carNumber = carNumber;
			this.time = time;
			this.intTypeCarNumber = formatCarNumberToInt(carNumber);
		}

		private int formatCarNumberToInt(String carNumber) {
			String[] splitedCarNumber = carNumber.split("");
			for (int i = 0; i < splitedCarNumber.length; i++) {
				if (splitedCarNumber[i].equals("0")) {
					carNumber.substring(1);
					continue;
				}
				break;
			}
			if (carNumber.equals("")) {
				carNumber = "0";
			}
			return Integer.parseInt(carNumber);
		}

		@Override
		public int compareTo(CarFee o) {
			return this.intTypeCarNumber - o.intTypeCarNumber;
		}
	}
}
