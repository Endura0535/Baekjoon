import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        
        int[] answer = new int[photo.length];
        
        HashMap<String, Integer> memory = new HashMap<>();
        
        for(int i = 0; i < name.length; i++){
            memory.put(name[i], yearning[i]);
        }
        
        for(int i = 0; i < photo.length; i++){
            int score = 0;
            for(String s: photo[i]){
                if(memory.get(s) != null)
                    score += memory.get(s);
            }
            answer[i] = score;
        }
        
        return answer;
    }
}