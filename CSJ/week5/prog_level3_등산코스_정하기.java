import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    private static int minIntensity = Integer.MAX_VALUE;
	private static int minIntensitySummit = Integer.MAX_VALUE;
	private static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	private static Set<Integer> gateSet = new HashSet<>();
	private static Set<Integer> summitSet = new HashSet<>();

	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		for (int gate : gates) {
			gateSet.add(gate);
		}
		for (int summit : summits) {
			summitSet.add(summit);
		}
		initGraph(n, paths);
		dijkstra(gates, summits, n);
		return new int[] {minIntensitySummit, minIntensity};
	}

	private static void initGraph(int n, int[][] paths) {
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] path : paths) {
			if (gateSet.contains(path[0]) || summitSet.contains(path[1])) {
				graph.get(path[0]).add(new Edge(path[1], path[2]));
			} else if (gateSet.contains(path[1]) || summitSet.contains(path[0])) {
				graph.get(path[1]).add(new Edge(path[0], path[2]));
			} else {
				graph.get(path[0]).add(new Edge(path[1], path[2]));
				graph.get(path[1]).add(new Edge(path[0], path[2]));
			}
		}
	}

	private static void dijkstra(int[] gates, int[] summits, int n) {
		Queue<Edge> pq = new ArrayDeque<>();
		int[] costs = new int[n + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);

		for (int gate : gates) {
			pq.offer(new Edge(gate, 0));
			costs[gate] = 0;
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nIndex = now.index;
			int nCost = now.cost;

			if (nCost > costs[nIndex]) {
				continue;
			}
			for (Edge next : graph.get(nIndex)) {
				int dis = Math.max(costs[nIndex], next.cost);
				if (dis < costs[next.index]) {
					costs[next.index] = dis;
					pq.offer(new Edge(next.index, dis));
				}
			}
		}

		Arrays.sort(summits);
		for (int summit : summits) {
			if (minIntensity > costs[summit]) {
				minIntensity = costs[summit];
				minIntensitySummit = summit;
			}
		}
	}

	private static class Edge {
		int index;
		int cost;

		Edge(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}
	}
}
