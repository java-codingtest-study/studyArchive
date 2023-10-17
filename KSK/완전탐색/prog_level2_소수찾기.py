from itertools import permutations

def solution(numbers):
    answer = 0
    arr=[]
    per=[]
    for i in range(len(numbers)):
        arr.append(numbers[i])
    
    for i in range(1, len(numbers)+1):
        per += list(permutations(arr,i))
    
    new_arr=[]
    
    for p in per:
        n=int(''.join(p))
        new_arr.append(n)

    new_arr=list(set(new_arr))
    
    for num in new_arr:
        check = True
        if num < 2:
            check = False
        for i in range(2,num):
            if num % i == 0:
                check = False
                break #이거 안해줘서 시간초과 발생 !
        if check == True:
            answer+=1
    
    return answer