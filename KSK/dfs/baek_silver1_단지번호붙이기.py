from collections import deque

N=int(input())

graph = [] 
result = []  
count = 0

for _ in range(N):
    graph.append(list(map(int, input().rstrip())))


dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def dfs(x, y):
    global count

    if x < 0 or x >= N or y < 0 or y >= N:
        return

    if graph[x][y] == 1:
        count += 1
        graph[x][y] = 0
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            dfs(nx, ny)


for i in range(N):
    for j in range(N):
        if graph[i][j] == 1:
            dfs(i, j)
            result.append(count)
            count = 0

result.sort()  # 오름차순으로 정렬

print(len(result))  # 총 단지수 출력
for k in result:  # 각 단지마다 집의 수 출력
    print(k)