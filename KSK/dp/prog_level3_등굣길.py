from collections import deque

def solution(m, n, puddles):
    MOD = 1000000007
    dx = [1, 0]
    dy = [0, 1]

    visited = [[0] * (m + 1) for _ in range(n + 1)]
    visited[1][1] = 1

    for px, py in puddles:
        visited[py][px] = -1 

    queue = deque([(1, 1)])

    while queue:
        x, y = queue.popleft()

        for i in range(2):
            nx, ny = x + dx[i], y + dy[i]

            if nx <= n and ny <= m and visited[nx][ny] != -1:
                if visited[nx][ny] == 0:
                    visited[nx][ny] = visited[x][y]
                    queue.append((nx, ny))
                else:
                    visited[nx][ny] = (visited[nx][ny] + visited[x][y]) % MOD

    return visited[n][m]


def solution(m, n, puddles):
    MOD = 1000000007
    dp = [[0] * (m+1) for _ in range(n+1)]
    dp[1][1] = 1
    
    for puddle in puddles:
        x,y = puddle
        dp[y][x] = -1
        
    for i in range(1,n+1):
        for j in range(1,m+1):
            if dp[i][j] == -1:
                dp[i][j] = 0
                continue
            dp[i][j] += (dp[i-1][j] + dp[i][j-1]) % MOD
    
    return dp[n][m]