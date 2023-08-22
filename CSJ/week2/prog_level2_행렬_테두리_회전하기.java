import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

class Solution {
    static int x1, y1;
	static int x2, y2;
	static Queue<Integer> queue;
	static int[][] graph;
	static ArrayList<Integer> numbers;

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] result = new int[queries.length];
		int i = 0;
		initGraph(rows, columns);

		for (int[] query : queries) {
			y1 = query[0];
			x1 = query[1];
			y2 = query[2];
			x2 = query[3];
			initQueue();
			moveGraph();
			result[i++] = Collections.min(numbers);
		}

		return result;
	}

	private static void moveGraph() {
		// 윗줄
		for (int x = x1; x < x2; x++) {
			graph[y1 - 1][x] = queue.poll();
		}

		//오른쪽 세로줄
		for (int y = y1; y < y2; y++) {
			graph[y][x2-1] = queue.poll();
		}

		//아랫줄
		for(int x = x2-2; x >= x1-1; x--) {
			graph[y2-1][x] = queue.poll();
		}

		//왼쪽 세로줄
		for(int y = y2-2; y >= y1-1; y--) {
			graph[y][x1-1] = queue.poll();
		}
	}

	private static void initQueue() {
		numbers = new ArrayList<>();
		queue = new ArrayDeque<>();


		// 윗줄
		for (int x = x1 - 1; x < x2; x++) {
			queue.add(graph[y1 - 1][x]);
			numbers.add(graph[y1 - 1][x]);
		}

		//오른쪽 세로줄
		for (int y = y1; y < y2; y++) {
			queue.add(graph[y][x2-1]);
			numbers.add(graph[y][x2-1]);
		}

		//아랫줄
		for(int x = x2-2; x >= x1-1; x--) {
			queue.add(graph[y2-1][x]);
			numbers.add(graph[y2-1][x]);
		}

		//왼쪽 세로줄
		for(int y = y2-2; y >= y1; y--) {
			queue.add(graph[y][x1-1]);
			numbers.add(graph[y][x1-1]);
		}
	}

	private static void initGraph(int rows, int columns) {
		graph = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < columns; k++) {
				graph[i][k] = (i * columns) + (k + 1);
			}
		}
	}
}
