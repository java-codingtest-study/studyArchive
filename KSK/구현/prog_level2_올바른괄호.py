def solution(s):
    answer = True
    cnt =0
    arr = list(s)
    
    for s in arr:
        
        if s =='(':
            cnt+=1
        if s ==')':
            cnt-=1
            
        if cnt < 0:
            answer =False
            break
        
    if cnt == 0:
        answer = True
    else: 
        answer = False
        
        
    return answer
