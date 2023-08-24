def solution(plans):
    plans.sort(key=lambda x: x[1])  # 시작 시각을 기준으로 과제들을 정렬

    answer = []  # 완료된 과제들을 담을 리스트
    stack = []  # 현재 진행 중이거나 잠시 멈춰둔 과제들을 관리하기 위한 스택

    for name, start, time in plans:
        h, m = map(int, start.split(':'))
        start = 60 * h + m  
        time = int(time)  

        if stack:
            prev_name, prev_start, prev_time = stack.pop()  # 스택에서 가장 최근에 추가된 과제를 꺼낸다
            
            extra_time = start - prev_start  # 현재 과제 시작 시간과 이전 과제 종료 시간 사이의 시간 차이를 계산

            if extra_time < prev_time:  # 이전 과제를 멈추고 새로운 과제를 시작해야 하는 경우
                stack.append((prev_name, prev_start, prev_time - extra_time))
            
            else:  # 이전 과제를 완료한 경우
                answer.append(prev_name)  # 이전 과제를 완료한 순서대로 정답 리스트에 추가
                extra_time = extra_time - prev_time

                while stack and extra_time:  # 잠시 멈춰둔 과제들을 이어서 진행
                    prev_name, prev_start, prev_time = stack.pop()

                    if extra_time < prev_time:  # 이전 과제를 멈추고 새로운 과제를 시작해야 하는 경우
                        stack.append((prev_name, prev_start, prev_time - extra_time))
                        break
                    else:  # 이전 과제를 완료한 경우
                        answer.append(prev_name)  # 이전 과제를 완료한 순서대로 정답 리스트에 추가
                        extra_time = extra_time - prev_time

        stack.append((name, start, time))  # 새로운 과제를 스택에 추가

    answer.extend([name for name, start, time in stack[::-1]])  # 남은 과제들을 정답 리스트에 추가

    return answer  # 최종적으로 완료된 과제들을 반환