
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(int[] elements) {
		int range = elements.length;
		Set<Integer> nums = new HashSet<>();

		for(int i = 1; i <= elements.length; i++){
			for(int j = 0; j < elements.length; j++) {
				int start = j;
				int end = start+i;

				int sumValue = 0;
				for(int k = start; k < end; k++) {
					sumValue += elements[k%range];
				}
				nums.add(sumValue);
			}
		}

		return nums.size();
	}
}
