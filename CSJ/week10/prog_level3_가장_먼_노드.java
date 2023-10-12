import java.util.*;

class Solution {
    public static int solution(int n, int[][] edge) {
		int answer = 0;
		Map<Integer, List<Integer>> graph = new HashMap<>();
		init(n, edge, graph);

		List<Integer> counter = new ArrayList<>();

		boolean[] visited = new boolean[n + 1];
		visited[1] = true;

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(1, 0));

		while(!q.isEmpty()) {
			Node now = q.poll();
			counter.add(now.count);

			for (int next : graph.get(now.position)) {
				if(!visited[next]) {
					q.add(new Node(next, now.count+1));
					visited[next] = true;
				}
			}
		}

		Collections.sort(counter);
		int maxCount = counter.get(counter.size() - 1);

		answer = Long.valueOf(counter.stream()
			.filter(c -> c == maxCount)
			.count()).intValue();

		return answer;
	}

	private static class Node {
		int position;
		int count;

		public Node(int position, int count) {
			this.position = position;
			this.count = count;
		}
	}

	private static void init(int n, int[][] edge, Map<Integer, List<Integer>> graph) {
		for (int i = 1; i <= n; i++) {
			graph.put(i, new ArrayList<>());
		}

		for (int[] e : edge) {
			graph.get(e[0]).add(e[1]);
			graph.get(e[1]).add(e[0]);
		}
	}
}
