import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Solution {

	private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static int solution(int n, int s, int a, int b, int[][] fares) {

		int answer = Integer.MAX_VALUE;

		initGraph(n, fares);
		int[] startA = new int[n+1];
		int[] startB = new int[n+1];
		int[] start = new int[n+1];

		Arrays.fill(startA, Integer.MAX_VALUE);
		Arrays.fill(startB, Integer.MAX_VALUE);
		Arrays.fill(start, Integer.MAX_VALUE);

		startA = dijkstra(a, startA);
		startB = dijkstra(b, startB);
		start = dijkstra(s, start);

		for(int i = 0; i <= n; i++) {
			// [start에서 i가는 비용 + (i에서 A가는 비용 + i에서 B가는 비용)] 중 최솟값 구하기
			answer = Math.min(start[i] + startA[i] + startB[i], answer);
		}

		return answer;
	}

	private static void initGraph(int n, int[][] fares) {
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] fare : fares) {
			graph.get(fare[0]).add(new Node(fare[1], fare[2]));
			graph.get(fare[1]).add(new Node(fare[0], fare[2]));
		}
	}

	static int[] dijkstra(int start, int[] costs) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		costs[start] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nIndex = now.index;
			int nCost = now.cost;

			if (nCost > costs[nIndex]) {
				continue;
			}

			ArrayList<Node> edges = graph.get(nIndex);
			for (Node edge : edges) {
				int cost = edge.cost + nCost;
				if(cost < costs[edge.index]) {
					costs[edge.index] = cost;
					pq.offer(new Node(edge.index, cost));
				}
			}
		}
		
		return costs; 
	}

	private static class Node implements Comparable<Node> {
		private int index;
		private int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
