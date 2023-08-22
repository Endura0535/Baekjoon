import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey != 0){
            int x = storey % 10;
            boolean plus = true;
            if(x < 10-x){
                plus = false;              
            }else if (x == 5){
                if((storey % 100) < 50)
                    plus = false;               
            }
            
            if(!plus){
                answer += x;
                storey /= 10;
            }else{
                answer += 10 - x;
                storey /= 10; 
                storey ++;
            }
        }
        return answer;
    }
}