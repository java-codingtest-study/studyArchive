from collections import deque

def solution(queue1, queue2):
    answer = 0
    
    total = (sum(queue1) + sum(queue2))
    if total % 2 != 0:
        answer = -1
        return answer
    
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    total1 = sum(queue1)
    total2 = sum(queue2)
    
    limit = len(queue1) * 4
    
    while True:
        if total1 > total2:
            tmp = (queue1.popleft())
            queue2.append(tmp)
            total1-=tmp
            total2+=tmp
            answer+=1
        elif total2 > total1:
            tmp = (queue2.popleft())
            queue1.append(tmp)
            total2-=tmp
            total1+=tmp
            answer+=1
        else:
            break
        
        if limit == answer:
            answer = -1
            break
        

    return answer