class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int now = 0;
        for(int s : section){
            if(now < s){
                now = s + m - 1;
                answer++;
            }
        }
        return answer;
    }
}