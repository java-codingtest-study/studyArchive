class Solution {
    public int solution(int[] money) {
        int len = money.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        
        if(len == 3) {
            return Math.max(Math.max(money[0], money[1]), money[2]);
        }
        
        //첫번째 집을 터는 경우
        dp1[0] = money[0];
        dp1[1] = money[0];
        
        for(int i = 2; i < len-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        
        //첫번째 집을 안 터는 경우
        
        dp2[1] = money[1];
        
        for(int i = 2; i < len; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        return Math.max(dp1[len-2],dp2[len-1]);
    }
}
