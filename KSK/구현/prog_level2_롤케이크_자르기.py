from collections import Counter

def solution(topping):
    answer = 0
    
    me = Counter(topping)
    brother = set()
    
    for i in topping:
        me[i]-=1
        brother.add(i)
        
        if me[i] == 0:
            me.pop(i)
            
        if len(me) == len(brother):
            answer+=1

    return answer