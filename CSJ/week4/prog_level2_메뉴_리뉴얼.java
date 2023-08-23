import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
   private Map<String, Integer> counts;
	private StringBuilder sb = new StringBuilder();


	public String[] solution(String[] orders, int[] course) {
		ArrayList<String> answer = new ArrayList<>();
		for (int i = 0; i < course.length; i++) {
			counts = new HashMap<>();
			for (String order : orders) {
				boolean[] visited = new boolean[order.length()];
				String[] words = order.split("");
                Arrays.sort(words);
				combination(words, visited, 0, words.length, course[i]);
			}
            if(counts.isEmpty()) {
				continue;
			}

			List<Map.Entry<String, Integer>> entryList = new LinkedList<>(counts.entrySet());
			entryList.sort(new Comparator<>() {
				@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return o2.getValue() - o1.getValue();
				}
			});
			int max = entryList.get(0).getValue();
			for (Map.Entry<String, Integer> result : entryList) {
				if (result.getValue() >= 2 && result.getValue() >= max) {
					answer.add(result.getKey());
				} else{
					break;
				}
			}
		}
		Collections.sort(answer);
		String[] result = new String[answer.size()];
		for(int i = 0; i< answer.size();i++) {
			result[i] = answer.get(i);
		}

		return result;
	}

	void combination(String[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			String combi = getCombiResult(arr, visited, n);
			counts.put(combi, counts.getOrDefault(combi, 0) + 1);
			return;
		}

		for(int i=start; i<n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	String getCombiResult(String[] arr, boolean[] visited, int n) {
		sb.setLength(0);
		for(int i = 0; i < n; i++) {
			if(visited[i]) {
				sb.append(arr[i]);
			}
		}

		return sb.toString();
	}
}
