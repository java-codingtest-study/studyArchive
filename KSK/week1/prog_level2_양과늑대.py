def solution(info, edges):
    visited = [0] * len(info)  # 각 노드의 방문 여부를 저장하는 리스트를 생성
    cnt_sheep = []  # 가능한 양의 수 저장할 list
    
    def dfs(sheep, wolf):
        if sheep > wolf:  # 양이 더 많은 경우에만 cnt_sheep에 양의 수를 추가
            cnt_sheep.append(sheep)
        else:    # 늑대가 더 많거나 같은 경우에는 스탑
            return  
        
        for p, c in edges:
            if visited[p] and not visited[c]:  # 부모 노드를 방문했고 자식 노드를 아직 방문하지 않았을 때
                visited[c] = 1  # 자식 노드 방문 처리
                if info[c] == 0:  # 자식 노드가 양이면 양의 수를 증가
                    dfs(sheep + 1, wolf)
                else:  # 자식 노드가 늑대면 늑대의 수를 증가
                    dfs(sheep, wolf + 1)
                visited[c] = 0  # 백트래킹을 위해 자식 노드 방문 여부를 원래대로

    visited[0] = 1  # 루트 노드부터 시작하므로 루트 노드 방문 처리
    dfs(1, 0)  # 처음에 양 1마리, 늑대 0마리로 시작
    
    answer = max(cnt_sheep)
    return answer  # 가능한 양의 수 중 최대값