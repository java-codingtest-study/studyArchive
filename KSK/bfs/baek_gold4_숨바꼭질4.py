from collections import deque

n, k = map(int, input().split())
visited = [0] * 100001

check = [0] * 100001

def move(now):
    data = []
    temp = now
    for _ in range(visited[now]+1):
        # 현재 위치 추가
        data.append(temp)
        # 전의 위치 받기
        temp = check[temp]
    print(' '.join(map(str, data[::-1])))


def bfs():
    q = deque()
    # 수빈이의 시작 위치
    q.append(n)
    while q:
        now = q.popleft()
        if now == k:
            # 걸린 시간 출력
            print(visited[now])
            # 어떻게 움직였는지 출력
            move(now)
            return
        for next_node in (now-1, now+1, now*2):
            if  0 <= next_node <= 100000 and visited[next_node] == 0:
                visited[next_node] = visited[now] + 1
                # 큐에 이동할 위치를 추가
                q.append(next_node)
                # 수빈이가 지나온 위치를 알기위해 다음 이동위치에 현재 이동위치를 기록
                check[next_node] = now

bfs()