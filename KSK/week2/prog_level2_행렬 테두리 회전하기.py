def solution(rows, columns, queries):
    answer = []
    arr =[]
    
    for i in range(rows): # array 생성
        arr1 =[]
        for j in range(columns):
            arr1.append(i * columns + j + 1)
        arr.append(arr1)
    
    for query in queries:
        y1, x1, y2, x2 = query
        y1, x1, y2, x2 = y1 - 1, x1 - 1, y2 - 1, x2 - 1  # 0-based indexing
        
        min_value = arr[y1][x1]
        prev = arr[y1][x1]
        
        # 위쪽 테두리 이동
        for i in range(x1 + 1, x2 + 1):
            curr = arr[y1][i]
            arr[y1][i] = prev
            min_value = min(min_value, curr)
            prev = curr
        
        # 오른쪽 테두리 이동
        for i in range(y1 + 1, y2 + 1):
            curr = arr[i][x2]
            arr[i][x2] = prev
            min_value = min(min_value, curr)
            prev = curr
        
        # 아래쪽 테두리 이동
        for i in range(x2 - 1, x1 - 1, -1):
            curr = arr[y2][i]
            arr[y2][i] = prev
            min_value = min(min_value, curr)
            prev = curr
        
        # 왼쪽 테두리 이동
        for i in range(y2 - 1, y1 - 1, -1):
            curr = arr[i][x1]
            arr[i][x1] = prev
            min_value = min(min_value, curr)
            prev = curr
        
        answer.append(min_value)
    
    return answer
