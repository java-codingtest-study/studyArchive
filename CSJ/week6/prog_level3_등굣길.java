class Solution {
    private static int[][] map;
    private static final int MOD = 1000000007;

	public static int solution(int m, int n, int[][] puddles) {
		int answer;
		initMap(m, n, puddles);

		// 1,1 ~ n,m 을 돌면서 도착 가능한 경우 체크
		for (int i = 1; i <= n; i++) {
			for (int k = 1; k <= m; k++) {
				if (map[i][k] == -1) { // 현재 위치가 물에 잠겼으면 continue
					continue;
				}
				// 오른쪽, 아래로만 움직일 수 있기 때문에, 이전 값인 위쪽, 왼쪽을 활용하여 경우의 수 탐색
				if (map[i][k - 1] >= 1 && map[i - 1][k] >= 1) { //왼쪽 과 위쪽 모두 방문 가능한 공간이면(1 이상) 두 경우의 합이 현재 위치 방문 가능 경우 수
					map[i][k] = (map[i][k - 1] + map[i - 1][k]) % MOD;
				} else if (map[i][k - 1] >= 1 && map[i - 1][k] < 1) { // 왼쪽은 방문 가능하지만 위쪽은 방문 불가능한 지역이면 왼쪽 방문 가능 경우 수 이어가기
					map[i][k] = map[i][k - 1] % MOD;
				} else if (map[i - 1][k] >= 1 && map[i][k - 1] < 1) { // 위쪽은 방문 가능하지만 왼쪽은 방문 불가능한 지역이면 위쪽 방문 가능 경우 수 이어가기
					map[i][k] = map[i - 1][k] % MOD;
				}
			}
		}
		answer = map[n][m];
		return answer;
	}

	// 지도 초기화
	private static void initMap(int m, int n, int[][] puddles) {
		map = new int[n + 1][m + 1];
		map[1][1] = 1; //1,1부터 시작하기 때문에 1,1에 도착 가능한 갯수 1로 초기화 후 시작
		for (int[] puddle : puddles) { // 물에 잠긴 지역 -1로 표시
			map[puddle[1]][puddle[0]] = -1;
		}
	}
}
