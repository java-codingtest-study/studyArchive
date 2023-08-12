// [주요 정보]
/*
- 가장 처음에 아기 상어의 크기는 2
- 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동
	- 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다
- 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
- 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값(BFS 인데 아래와 같은 조건이 있기 때문에 일반 큐가 아닌 우선순위 큐 사용)
	[물고기 먹기 규칙]
	1. 거리가 가까운 순서
	2. 거리가 같다면 y가 작은 순서
	3. y가 같다면 x가 작은 순서
- 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
	- 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		Queue<Node> queue;
		Node cur = null;

		int time = 0;
		int[] dx = {0, -1, 0, 1};
		int[] dy = {-1, 0, 1, 0};

		int size = 2;
		int eatCount = 0;
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] m = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(m[j]);
				if (m[j].equals("9")) {
					cur = new Node(j, i, 0);
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			queue = new PriorityQueue<>();
			boolean wasEat = false;
			boolean[][] visited = new boolean[N][N];
			queue.add(cur);
			visited[cur.y][cur.x] = true;

			while (!queue.isEmpty()) {
				Node now = queue.poll();

				if (map[now.y][now.x] > 0 && map[now.y][now.x] < size) {
					wasEat = true;
					eatCount++;
					time += now.distance;
					cur = new Node(now.x, now.y, 0);
					map[cur.y][cur.x] = 0;
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || map[ny][nx] > size) {
						continue;
					}
					queue.add(new Node(nx, ny, now.distance + 1));
					visited[ny][nx] = true;

				}
			}

			if (wasEat) {
				if(eatCount == size) {
					size++;
					eatCount = 0;
				}
				continue;
			}
			break;

		}

		System.out.println(time);

	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int distance;

		public Node(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			if (this.distance != o.distance) {
				return this.distance - o.distance;
			}
			if (this.y != o.y) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}
}
