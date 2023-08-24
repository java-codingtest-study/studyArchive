import sys
input = sys.stdin.readline

# 테스트 케이스의 개수
t = int(input())

for _ in range(t):
    # 페이지 수 
    k = int(input())

    # 페이지 값들을 리스트로 
    page = list(map(int, input().split()))

    # 계산된 값을 저장할 테이블 초기화
    table = [[0]*k for _ in range(k)]

    # 연속된 페이지에 대한 테이블 값 채우기
    for i in range(k-1):
        table[i][i+1] = page[i] + page[i+1]
        for j in range(i+2, k):
            table[i][j] = table[i][j-1] + page[j]

    # dp 사용하여 최적의 배치 찾기
    for d in range(2, k):
        for i in range(k-d):
            j = i + d
            # 각 가능한 분할 지점에 대한 최소 비용 계산
            minimum = [table[i][k] + table[k+1][j] for k in range(i, j)]
            table[i][j] += min(minimum)

    print(table[0][k-1])


    