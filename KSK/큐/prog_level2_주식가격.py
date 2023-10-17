from collections import deque
def solution(prices):
    answer = []
    
    q = deque(prices)
    
    while q:
        price = q.popleft()
        cnt=0
        for c in q:
            cnt+=1
            if c < price:
                break
        answer.append(cnt)
    
    return answer