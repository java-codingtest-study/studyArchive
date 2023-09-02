import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private static int[] percents = {10, 20, 30, 40};
	private static Set<ArrayList<Integer>> visited = new HashSet<>();
	private static int maxOfSubscribe = 0;
	private static int maxOfCost = 0;

	public static int[] solution(int[][] users, int[] emoticons) {
		ArrayList<Integer> discounts = new ArrayList<>();
		for (int i = 0; i < emoticons.length; i++) {
			discounts.add(10);
		}
		dfs(users, emoticons, discounts);
		return new int[] {maxOfSubscribe, maxOfCost};
	}

	private static void dfs(int[][] users, int[] emoticons, ArrayList<Integer> discounts) {
		int[] prices = new int[emoticons.length];
		int cost = 0;
		int subscribe = 0;

		for (int i = 0; i < emoticons.length; i++) {
			int emoticonPrice = (emoticons[i] / 100) * (100 - discounts.get(i));
			prices[i] = emoticonPrice;
		}
		for (int[] user : users) {
			int purchasePer = user[0];
			int subscribeStandard = user[1];
			int userCost = 0;
			boolean userSubscribe = false;

			for (int i = 0; i < discounts.size(); i++) {
				if (discounts.get(i) < purchasePer) {
					continue;
				}
				userCost += prices[i];

				if (userCost >= subscribeStandard) {
					userSubscribe = true;
					break;
				}
			}

			if (userSubscribe) {
				subscribe++;
			} else {
				cost += userCost;
			}
		}

		if (subscribe > maxOfSubscribe || (subscribe == maxOfSubscribe && cost > maxOfCost)) {
			maxOfCost = cost;
			maxOfSubscribe = subscribe;
		}

		for (int i = 0; i < discounts.size(); i++) {
			if (discounts.get(i) == 40) {
				continue;
			}
			ArrayList<Integer> nextDiscounts = (ArrayList<Integer>)discounts.clone();
			nextDiscounts.set(i, discounts.get(i) + 10);
			if (visited.contains(nextDiscounts)) {
				continue;
			}
			visited.add(nextDiscounts);
			dfs(users, emoticons, nextDiscounts);
		}
	}
}
