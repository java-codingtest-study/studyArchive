import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class Solution {
    private static Map<String, Integer> termInfo = new HashMap<>();
	private static int[] todayDate;
	private static final int DAY_OF_MONTH = 28;

	public static int[] solution(String today, String[] terms, String[] privacies) {
		int[] answer = {};

		todayDate = toCalendar(today);
		initTermData(terms);

		Vector<Integer> ans = new Vector<>();
		for (int i = 0; i < privacies.length; i++) {
			String[] p = privacies[i].split(" ");

			int[] contractDate = toCalendar(p[0]);
			int term = termInfo.get(p[1]);

			contractDate[2] += term;

			int contractDays =
				(contractDate[0] * 12 * DAY_OF_MONTH) + (contractDate[1] * DAY_OF_MONTH) + contractDate[2] - 1;
			int todayDays = (todayDate[0] * 12 * DAY_OF_MONTH) + (todayDate[1] * DAY_OF_MONTH) + todayDate[2];

			if (todayDays > contractDays) {
				ans.add(i + 1);
			}
		}

		Collections.sort(ans);
		answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}

	private static int[] toCalendar(String todayStr) {
		int[] result = new int[3];
		String[] today = todayStr.split("\\.");

		for (int i = 0; i < today.length; i++) {
			String date = today[i];
			if (date.charAt(0) == '0') {
				date = date.substring(1);
			}
			result[i] = Integer.parseInt(date);
		}

		return result;
	}

	private static void initTermData(String[] terms) {
		for (String t : terms) {
			String[] termData = t.split(" ");
			String type = termData[0];
			int term = Integer.parseInt(termData[1]) * DAY_OF_MONTH;

			termInfo.put(type, term);
		}
	}
}
