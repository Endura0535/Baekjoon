import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int a : array){
            list.add(a);
        }
        
        int[] answer = new int[commands.length];
        
        for(int j = 0; j < commands.length; j++){
            ArrayList<Integer> choosenList = new ArrayList<>();
            for(int i = commands[j][0]; i <=commands[j][1]; i++){
                choosenList.add(list.get(i-1));
            }
            Collections.sort(choosenList);
            answer[j] = choosenList.get(commands[j][2]-1);
        }
        
        return answer;
    }
}