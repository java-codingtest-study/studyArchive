/**
* [문제 해결 아이디어]
* 
* 1. queue1, queue2 중 sum값이 더 큰 queue에서 작은 queue로 값을 넘겨준다.

* 2. 그리고 queue1의 sum, queue2의 sum이 같은 값인지 검사한다.
(sum 계산은 오버플로우 방지를 위해 long 타입을 사용한다)

* 3. 해당 과정을 queue 한 개의 원소값 * 3만큼 반복한다.
(왜 3개? : 
최대 이동 경우인 queueA에서 queueB로 원소가 넘어갔다가
queueB에 있는 모든 원소 중 1개를 제외하고 모든 원소가 queueA로 넘어오는 경우에서 필요한 반복 횟수 [즉 모든 경우를 찾기 위한 반복 횟수])
* 반복을 거치며 answer을 1씩 올리다가 queue1의 sum, queue2의 sum이 같은 값이라면 break하고 answer를 return한다.

* 4. queue 한 개의 원소값 * 3만큼 반복해도 결론이 나지 않으면 -1을 return한다
**/

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public static int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		int queueSize = queue1.length;

		Queue<Integer> intQueue1 = new ArrayDeque<>();
		Queue<Integer> intQueue2 = new ArrayDeque<>();
		long queue1Sum = 0;
		long queue2Sum = 0;

		for (int i = 0; i < queueSize; i++) {
			intQueue1.add(queue1[i]);
			intQueue2.add(queue2[i]);

			queue1Sum += queue1[i];
			queue2Sum += queue2[i];
		}

		while (true) {
			if(answer >= queueSize * 3) {
				answer = -1;
				break;
			}

			if (queue1Sum < queue2Sum) {
				int polledValue = intQueue2.poll();
				intQueue1.add(polledValue);

				queue2Sum -= polledValue;
				queue1Sum += polledValue;
			    
                answer++;
			} else if (queue1Sum > queue2Sum) {
				int polledValue = intQueue1.poll();
				intQueue2.add(polledValue);

				queue1Sum -= polledValue;
				queue2Sum += polledValue;
                
			    answer++;
			}

			if (queue1Sum == queue2Sum) {
				break;
			}
            
		}
		return answer;
	}
}
