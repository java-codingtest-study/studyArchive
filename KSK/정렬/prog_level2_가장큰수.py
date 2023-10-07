def solution(numbers):
    numbers = list(map(str, numbers))
    
    # 주어진 두 숫자를 합쳤을 때 더 큰 순서대로 정렬합니다.
    numbers.sort(key=lambda x: x*3, reverse=True)
    
    # 결과를 합쳐서 반환합니다.
    return str(int(''.join(numbers)))