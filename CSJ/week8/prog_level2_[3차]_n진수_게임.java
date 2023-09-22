class Solution {
    public static String solution(int n, int t, int m, int p) {
		StringBuilder sb = new StringBuilder();
		int maxValue = t * m + p-1; // 미리구할 갯수 * 참가 인원 + 튜브 순서 만큼 구해놔야 말해야하는 값을 구할 수 있음

		int v = 0;
		while(sb.length() < maxValue) {
			sb.append(Integer.toString(v++, n));
		}

		return findTalkingNumber(sb.toString().toUpperCase(), p-1, t, m);
	}

	private static String findTalkingNumber(String allNumber, int order, int talkNumberAmount, int memberAmount) {
		StringBuilder result = new StringBuilder();

		for(int i = order; i < allNumber.length(); i += memberAmount) {
			result.append(allNumber.charAt(i));
			if(result.length() >= talkNumberAmount) {
				break;
			}
		}

		return result.toString();
	}
}
