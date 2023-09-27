import java.util.*;


class Solution {
    public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		Queue<Integer> q = new ArrayDeque();

		for (int i = 0; i < progresses.length; i++) {
			int progress = progresses[i];
			int speed = speeds[i];

			int days = 0;
			while (progress < 100) {
				progress += speed;
				days++;
			}

			q.add(days);
		}

		int cnt = 0;
		int before = q.peek();
		List<Integer> arr = new ArrayList<>();
		while (!q.isEmpty()) {
			int v = q.poll();

			if (before >= v) {
				cnt++;
			} else {
				arr.add(cnt);
				cnt = 1;
				before = v;
			}

			if (q.isEmpty()) {
				arr.add(cnt);
			}
		}
		
		answer = new int[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			answer[i] = arr.get(i);
		}

		return answer;
	}
}
