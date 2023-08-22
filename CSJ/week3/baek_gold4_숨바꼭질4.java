package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,K;
	private static int[] visited = new int[100001];
	private static int[] parent = new int[100001];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs(N, K);

		System.out.println(visited[K]-1);

		Stack s = new Stack();

		int idx = K;
		while (idx != N) {
			s.push(idx);
			idx = parent[idx];
		}
		s.push(N);

		while(!s.isEmpty()){
			System.out.print(s.pop() + " ");
		}
	}

	private static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = 1;

		while (!q.isEmpty()) {
			int now = q.poll();

			if(now + 1 <= 100000 && visited[now+1] == 0) {
				visited[now+1] = visited[now]+1;
				parent[now+1] = now;
				q.add(now + 1);
			}
			if(now -1 >= 0 && visited[now-1] == 0) {
				visited[now - 1] = visited[now] + 1;
				parent[now-1] = now;
				q.add(now - 1);
			}
			if(now * 2 <= 100000 && visited[now*2] == 0) {
				visited[now*2] = visited[now]+1;
				parent[now*2] = now;
				q.add(now * 2);
			}

			if(visited[end] != 0) {
				break;
			}
		}
	}
}