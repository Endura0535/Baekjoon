import java.util.*;
import java.lang.Math;

class Solution {
    public int[] solution(int[] answers) {
        
        int[] supo1 = {1,2,3,4,5};
        int[] supo2 = {2,1,2,3,2,4,2,5};
        int[] supo3 = {3,3,1,1,2,2,4,4,5,5};
        
        int[] score = {scoring(supo1, answers), 
                      scoring(supo2, answers), 
                      scoring(supo3, answers)};
        
        int maxScore = 0;
        for(int s : score){
            maxScore = Math.max(maxScore, s);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 4; i++){
            if(score[i-1] == maxScore){
                list.add(i);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public int scoring(int[] supo, int[] answers){
        int rightCnt = 0;
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == supo[i%supo.length])
                rightCnt++;
        }
        return rightCnt;
    }
}