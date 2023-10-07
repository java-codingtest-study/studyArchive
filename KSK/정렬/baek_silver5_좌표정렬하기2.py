n = int(input())
arr=[]

for _ in range(n):
    x,y=list(map(int,input().split()))
    arr.append([y,x])

arr=sorted(arr)

for y, x in arr:
    print(x,y)