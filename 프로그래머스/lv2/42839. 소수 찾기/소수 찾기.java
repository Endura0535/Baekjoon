import java.util.*;
import java.lang.Math;

class Solution {
    
    ArrayList<Integer> list = new ArrayList<>();
    
    int answer = 0;
    public int solution(String numbers) {
        boolean[] visited = new boolean[numbers.length()];
        makeSubset(numbers, "", visited, 0);
        System.out.println(list);
        return answer;
    }
    
    public void makeSubset(String numbers, String number, boolean[] visited, int cnt){
        if(cnt == numbers.length())
            return;
        for(int i = 0; i < numbers.length(); i++){
            if(!visited[i]){
                int newNum = Integer.parseInt(number + numbers.charAt(i));
                if(isPrime(newNum) && list.indexOf(newNum) == -1){
                    list.add(newNum);
                    answer++;
                }
                visited[i] = true;
                makeSubset(numbers, number + numbers.charAt(i), visited, cnt + 1);
                visited[i] = false;
                makeSubset(numbers, number, visited, cnt + 1);
            }
        }
    }
    
    public boolean isPrime(int number){
        if(number == 0 || number == 1)
            return false;
        else if(number == 2)
            return true;
        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0)
                return false;
        }
        return true;
    }
}