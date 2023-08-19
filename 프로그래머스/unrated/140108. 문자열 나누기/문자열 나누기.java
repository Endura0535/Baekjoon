class Solution {
    public int solution(String s) {
        int answer = 0;
        int isX = 0, notX = 0;
        char x = 'a';
        for(int i = 0; i < s.length(); i++){
            if(isX == 0 && notX == 0){
                x = s.charAt(i);
                isX++;
            }else{
                if(x == s.charAt(i)){   // x와 같으면
                    isX++;
                }else{
                    notX++;
                }
                
                if(isX == notX){    // 2개의 개수가 같아지면 초기화하고 answer 업데이트
                    isX = 0;
                    notX = 0;
                    answer++;
                }
            }
        }
        
        if(isX != 0 || notX != 0)
            answer++;
        
        
        return answer;
    }
}