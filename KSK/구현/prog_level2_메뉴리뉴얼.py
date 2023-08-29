import itertools
from collections import Counter

def solution(orders, course):
    answer = []

    for course_size in course:
        course_combinations = []  # 현재 코스 크기에 대한 조합들을 저장할 리스트 초기화
        
        # 각 주문마다 가능한 조합
        for order in orders:
            course_combinations += list(itertools.combinations(sorted(order), course_size))
        
        counter = Counter(course_combinations)  # 조합의 빈도
        
        
        if len(counter) > 0:
            max_count = max(counter.values())  # 가장 높은 주문 횟수

            if max_count >= 2: # 최소 2번 이상 주문된 조합 중에서
                for comb, count in counter.items():
                    if count == max_count:
                        answer.append(''.join(comb))  # 가장 많이 주문된 조합을 정답 리스트에 추가
                
    return sorted(answer)