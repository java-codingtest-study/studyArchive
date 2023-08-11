from collections import deque

def solution(board):
    answer = -1
    
    n, m = len(board), len(board[0])  # 보드의 행과 열 크기

    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]  # 이동 가능한 방향

    # 보드를 순회하며 시작 위치와 목표 위치 찾음
    for x in range(n):
        for y in range(m):
            if board[x][y] == "R":
                sx, sy = x, y  # 시작 위치를 저장
    
    visited = [[0]*m for _ in range(n)]  # 방문 여부를 저장하는 2차원 배열을 초기화

    q = deque()  # BFS에 사용할 큐 생성

    q.append((sx, sy, 0))  # 시작 위치와 레벨 0을 큐에 추가
    
    while q:
        x, y, length = q.popleft()  # 큐에서 위치와 길이 정보를 pop
        
        if board[x][y] == "G":
            answer = length  # 현재 위치가 목표 위치인 경우 끝
            break
        
        # 현재 위치에서 네 방향으로 이동해보며 이동할 수 있는 범위 확인
        for dx, dy in direction:
            scope = 1
            while 1:
                nx, ny = x + dx * scope, y + dy * scope
                
                # 이동한 위치가 보드 밖이거나 장애물 D인 경우
                if nx < 0 or nx >= n or ny < 0 or ny >= m or board[nx][ny] == "D":
                    if visited[nx - dx][ny - dy] == 0:
                        visited[nx - dx][ny - dy] = 1  # 방문 체크
                        q.append((nx - dx, ny - dy, length + 1))  # 해당 위치를 큐에 추가하여 길이을 1 증가
                    
                    break 
                
                scope += 1  # 이동 범위 증가
    
    return answer  