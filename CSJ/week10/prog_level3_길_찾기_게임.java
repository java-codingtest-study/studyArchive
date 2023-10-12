import java.util.*;

class Solution {
    private static int idx1 = 0;
	private static int idx2 = 0;

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][];
		int nodeSize = 0;

		List<Node> nodes = sortNode(nodeinfo);
		nodeSize = nodes.size();

		Node top = getTop(nodes);

		initGraph(nodes, top);

		int[] result1 = new int[nodeSize];
		int[] result2 = new int[nodeSize];

		search1(result1, top);
		search2(result2, top);

		answer[0] = result1;
		answer[1] = result2;
		return answer;
	}

	private static void search1(int[] result, Node now) {
		result[idx1++] = now.num;
		if(now.left != null) {
			search1(result, now.left);
		}
		if(now.right != null) {
			search1(result, now.right);
		}
	}

	private static void search2(int[] result, Node now) {
		if(now.left != null) {
			search2(result, now.left);
		}
		if(now.right != null) {
			search2(result, now.right);
		}
		result[idx2++] = now.num;
	}

	private static Node getTop(List<Node> nodes) {
		Node top = nodes.get(0);
		nodes.remove(0);
		return top;
	}

	private static List<Node> sortNode(int[][] nodeinfo) {
		List<Node> nodes = new ArrayList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		Collections.sort(nodes);
		return nodes;
	}

	private static void initGraph(List<Node> nodes, Node top) {
		while (!nodes.isEmpty()) {
			Node now = getTop(nodes);

			Node before = top;
			while (true) {
				if (now.x < before.x) {
					if (before.left == null) {
						before.left = now;
						break;
					} else {
						before = before.left;
					}
				} else if (now.x > before.x) {
					if (before.right == null) {
						before.right = now;
						break;
					} else {
						before = before.right;
					}
				}
			}
		}
	}

	private static class Node implements Comparable<Node> {
		private int num;
		private int x;
		private int y;
		private Node left;
		private Node right;

		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
			left = null;
			right = null;
		}

		@Override
		public int compareTo(Node o) {
			return o.y - this.y;
		}
	}
}
