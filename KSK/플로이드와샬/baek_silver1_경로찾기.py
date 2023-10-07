#플로이드 와샬
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

for i in range(n):
    for j in range(n):
        for k in range(n):
            if graph[j][i] ==1 and graph[i][k] ==1:
                graph[j][k] = 1

for g in graph:
    print(*g)

# BFS
from collections import deque

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[0]*n for _ in range(n)] # 방문 표시

def bfs(x):
    queue = deque()
    queue.append(x)
    check = [0 for _ in range(n)] # 연결되어 있다면

    while queue:
        q = queue.popleft()

        for i in range(n):
            if check[i] == 0 and graph[q][i] == 1:
                queue.append(i)
                check[i]=1
                visited[x][i] =1

for i in range(n):
    bfs(i)

for v in visited:
    print(*v)


# DFS
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [0 for _ in range(n)] # 방문 표시

def dfs(x):
    for i in range(n):
        if graph[x][i] == 1 and visited[i] ==0:
            visited[i]=1
            dfs(i)
            
visited = [0 for _ in range(n)]
for i in range(n):
    dfs(i)
    for j in range(n):
        if visited[j] ==1:
            print(1,end=' ')
        else:
            print(0, end =' ')
    print()
    visited = [0 for _ in range(n)]
