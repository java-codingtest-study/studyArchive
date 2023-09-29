from collections import deque
def solution(operations):
    answer = []
    q = deque()
    for operation in operations:
        op, num=operation.split(' ')
        num = int(num)
        if op == "I":
            q.append(num)
        if op == "D" and num == 1 and q:
            max_num = max(q)
            q.remove(max_num)
        if op == "D" and num == -1 and q :
            min_num = min(q)
            q.remove(min_num)

    if len(q)==0:
        answer=[0,0]
    else:
        answer.append(max(q))
        answer.append(min(q))
        
    return answer