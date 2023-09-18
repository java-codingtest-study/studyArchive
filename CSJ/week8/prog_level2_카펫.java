class Solution {
    public static int[] solution(int brown, int yellow) {
		int[] answer = {};
		int row = 1;
		int col = yellow;
        
		while (true) {
			if (getRound(row, col) == brown) {
				answer = new int[] {col+2, row+2};
				break;
			}
            while(true) {
			    row++;
			    if(yellow % row == 0) {
                    col = yellow/row;
                    break;
                }
            }
		}

		return answer;
	}

	public static int getRound(int row, int col) {
        System.out.println(col+" "+row);
		return col * 2 + row * 2 + 4;
	}
}
