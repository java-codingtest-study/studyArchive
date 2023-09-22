import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(String[] operations) {
		int[] answer = {0, 0};
		final String MINUS_MAX_VALUE = "D 1";
		final String MINUS_MIN_VALUE = "D -1";
		final char INSERT_SIGN = 'I';

		List<Integer> pq = new ArrayList<>();

		for (String operation : operations) {
			if(operation.charAt(0) == INSERT_SIGN){
				int value = Integer.parseInt(operation.substring(2));
				pq.add(value);
				Collections.sort(pq);
				continue;
			}
			if(pq.isEmpty()) {
				continue;
			}
			if(operation.equals(MINUS_MAX_VALUE)){
				pq.remove(pq.size() - 1);
			} else if(operation.equals(MINUS_MIN_VALUE)) {
				pq.remove(0);
			}

		}
		if(pq.isEmpty()) {
			return answer;
		}
		answer = new int[] {pq.get(pq.size() - 1), pq.get(0)};
		return answer;
	}
}
