package week1;// [문제 해결 아이디어]
// 모든 경우를 탐색하기 때문에 dfs 활용

// 0번 인덱스 부터 시작하므로 dfs의 idx값에 0을 넣어 시작
// 방문한 인덱스를 visited 체크
// 방문한 인덱스의 info가 0(양)이면 sheepCount++, maxSheepCount체크
// 방문한 인덱스의 info가 1(늑대)이면 wolfCount++
//
// 부모는 방문했지만 자식노드는 방문하지 않은 모든 노드를 찾아 방문한다.
// 모든 경우를 탐색하기 위해 현재 visited 를 완전카피하여 dfs

class Solution {
    
	static int[] gInfo;
	static int[][] gEdges;
	static int maxSheepCount;

	public static int solution(int[] info, int[][] edges) {
		gInfo = info;
		gEdges = edges;
		boolean[] visited = new boolean[info.length];

		dfs(visited, 0,0,0);

		return maxSheepCount;
	}

	static void dfs(boolean[] visited, int idx, int sheepCount, int wolfCount) {
		visited[idx] = true;

		if(gInfo[idx] == 0) {
			sheepCount++;
			maxSheepCount = Math.max(sheepCount, maxSheepCount);
		} else if(gInfo[idx] == 1) {
			wolfCount++;
		}

		if(wolfCount >= sheepCount) {
			return;
		}

		for(int[] edge : gEdges) {
			if(visited[edge[0]] && !visited[edge[1]]) {
				boolean[] nextVisited = new boolean[visited.length];
				for (int i = 0; i < visited.length; i++) {
					nextVisited[i] = visited[i];
				}
				dfs(nextVisited, edge[1], sheepCount, wolfCount);
			}
		}
	}

}
