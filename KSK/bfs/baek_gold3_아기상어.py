import sys
from collections import deque

INF = 1e9
input = sys.stdin.readline

n=int(input()) #공간의 크기
state=[] #공간의 상태를 저장하는 이차원 배열
for i in range(n):
    state.append(list(map(int, input().split())))


cnt = 0
shark_size = 2
now_x, now_y = 0, 0

#아기상어 위치 확인
for i in range(n):
    for j in range(n):
        if state[i][j] == 9:
            state[i][j] = 0
            now_x = i
            now_y = j

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(): #각각의 위치 까지의 최단 거리를 구하는 함수
    distance = [[-1] * n for _ in range(n)]
    dq = deque([(now_x,now_y)])
    distance[now_x][now_y] = 0
    

    while dq:
        x, y = dq.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < n: #위치가 범위 안
                if state[nx][ny] <= shark_size and distance[nx][ny] == -1: # 이동가능 할 때
                    distance[nx][ny] = distance[x][y] + 1
                    dq.append((nx, ny))
    return distance

def find(distance):
    x, y = 0, 0
    min_dist = INF #최단 거리 계산 할 때

    for i in range(n):
        for j in range(n):
            if distance[i][j] != -1 and 1 <= state[i][j]<shark_size: #먹을 수 있어!
                if distance[i][j] < min_dist:
                    x, y = i, j
                    min_dist = distance[i][j]

    if min_dist == INF: #먹을 물고기가 없는 경우
        return None
    else:
        return x, y, min_dist


result = 0

while True:
    value = find(bfs())
    if value == None:
        print(result)
        break
    else: 
        now_x, now_y = value[0], value[1]
        result += value[2]
        state[now_x][now_y] = 0
        cnt +=1

    if cnt >= shark_size:
        shark_size += 1
        cnt = 0
