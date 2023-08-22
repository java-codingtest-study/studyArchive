
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 참고 https://guy-who-writes-sourcecode.tistory.com/43
//점화식 : dp[i][i+divide] + dp[divide+1][j] + sum(i~j까지)
public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int K;
	private static int[][] dp;
	private static int[] novel;
	private static int[] sum;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			K = Integer.parseInt(br.readLine());
			dp = new int[K + 1][K + 1];
			novel = new int[K + 1];
			sum = new int[K + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				novel[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + novel[i]; //
			}

			for (int n = 1; n < K; n++) { // 몇장씩 묶을 것인가
				for (int from = 1; from + n <= K; from++) { // 시작점 정하기 ( from+n을 to로 잡기 때문에 결국 to가 K보다 커지기 전까지 반복 )
					int to = from + n; // n장씩 묶을 것이기 때문에 to = from + n
					dp[from][to] = Integer.MAX_VALUE;
					for (int divide = from; divide < to; divide++) { // 123이면 1 23 / 12 3 이런식으로 나누기 위한 구분자로 divide 사용
						dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + (sum[to] - sum[from - 1]));
					}
				}
			}
			System.out.println(dp[1][K]);
		}

	}
}
