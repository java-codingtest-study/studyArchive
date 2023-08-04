package programmers;

// [문제 해결 아이디어]
// 최소 이동 도착이기 때문에 BFS 사용
// visited 체크는 queue에 넣을 때가 아닌 실제로 도달했을 때 체크

// [이동 방법]
// 동서남북 방향으로 미끄러져 이동
// 이동을 멈추는 규칙 : (X or Y 가 0일 때) OR (다음 이동 방향 좌표의 값이 D일 때)

// [결과 리턴 방식]
// result 를 -1로 초기화 후 bfs 탐색 시작
// 이동을 멈춘 좌표의 값이 G일 때 이동 횟수를 result에 저장 후 stop
// 더 이상 이동할 수 있는 곳이 없을 때(Queue가 비었을 때) stop
// result return

import java.util.ArrayDeque;
import java.util.Queue;

public class Week1_2 {

	public static void main(String[] args) {
		String[] board = {".D.R", "....", ".G..", "...D"};
		System.out.println(solution(board));
	}

	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static int x, y;

	static Queue<Node> queue = new ArrayDeque<>();
	static boolean[][] visited = {};
	static String[][] graph = {};
	static int WIDTH;
	static int HEIGHT;

	public static int solution(String[] board) {
		HEIGHT = board.length;
		WIDTH = board[0].length();

		graph = new String[HEIGHT][WIDTH];
		visited = new boolean[HEIGHT][WIDTH];

		Node startNode = init(board);

		return bfs(startNode);
	}

	private static int bfs(Node startNode) {
		int answer = -1;

		queue.add(startNode);

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			x = now.x;
			y = now.y;
			if (visited[x][y]) {
				continue;
			}
			if (graph[x][y].equals("G")) {
				answer = now.depth;
				break;
			}

			visited[x][y] = true;

			// TODO: 미끄러지는 로직 구현
			for (int i = 0; i < 4; i++) {
				// 북쪽으로 이동할 때는 다음 칸에 장애물이 없고, x가 0보다 클 때 이동
				if (i == 0) {
					moveNorth(now, i);
				}
				// 남쪽으로 이동할 때는 다음 칸에 장애물이 없고, x가 HEIGHT-1보다 작을 때 이동
				else if (i == 1) {
					moveSouth(now, i);
				}
				// 서쪽으로 이동할 때는 다음 칸에 장애물이 없고, y가 0보다 클 떄 이동
				else if (i == 2) {
					moveWest(now, i);
				}
				// 동쪽으로 이동할 때는 다음 칸에 장애물이 없고, y가 WIDTH-1 보다 작을 때 이동
				else if (i == 3) {
					moveEast(now, i);
				}

			}
		}

		return answer;
	}

	private static void moveNorth(Node now, int i) {
		int nx = x;
		int ny = y;

		for (int j = x; j > 0; j--) {
			if (graph[nx + dx[i]][ny + dy[i]].equals("D"))
				break;
			nx += dx[i];
			ny += dy[i];
		}
		queue.add(new Node(nx, ny, now.depth + 1));
	}

	private static void moveSouth(Node now, int i) {
		int nx = x;
		int ny = y;

		for (int j = x; j < HEIGHT - 1; j++) {
			if (graph[nx + dx[i]][ny + dy[i]].equals("D"))
				break;
			nx += dx[i];
			ny += dy[i];
		}
		queue.add(new Node(nx, ny, now.depth + 1));
	}

	private static void moveEast(Node now, int i) {
		int nx = x;
		int ny = y;

		for (int j = y; j < WIDTH - 1; j++) {
			if (graph[nx + dx[i]][ny + dy[i]].equals("D"))
				break;
			nx += dx[i];
			ny += dy[i];
		}
		queue.add(new Node(nx, ny, now.depth + 1));
	}

	private static void moveWest(Node now, int i) {
		int nx = x;
		int ny = y;

		for (int j = y; j > 0; j--) {
			if (graph[nx + dx[i]][ny + dy[i]].equals("D"))
				break;
			nx += dx[i];
			ny += dy[i];
		}
		queue.add(new Node(nx, ny, now.depth + 1));
	}

	public static class Node {
		int x;
		int y;
		int depth;

		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}

	private static Node init(String[] board) {
		Node start = null;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				visited[i][j] = false;
				graph[i][j] = Character.toString(board[i].charAt(j));
				start = checkStartNode(start, i, j);
			}
		}

		return start;
	}

	private static Node checkStartNode(Node start, int i, int j) {
		if (graph[i][j].equals("R")) {
			start = new Node(i, j, 0);
		}
		return start;
	}
}
