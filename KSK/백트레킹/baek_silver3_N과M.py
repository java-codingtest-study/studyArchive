n, m = map(int, input().split())

s=[]
visited=[0]*(n+1)

def dfs():
    if len(s) == m:
        print(' '.join(map(str,s)))
        return
    
    for i in range(1,n+1):
        if visited[i]==1: # 만약 이미 방문한 자연수라면, 넘어갑니다.
            continue
        visited[i] = 1 #자연수 i를 방문했다고 표시
        s.append(i)
        dfs() #순열을 하나 더 만들기 위해 재귀 호출
        s.pop()# 리스트에서 마지막에 추가된 원소를 제거합니다. 이전 단계로 돌아가서 다른 자연수를 선택할 수 있도록 합니다.
        visited[i] = 0

dfs()