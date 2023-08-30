import java.util.*;

class Solution {
    
    // 델타배열 : NSWE
    int [] dx = {0,0,-1,1};
    int [] dy = {-1,1,0,0};
    
    public int[] solution(String[] park, String[] routes) {
        
        char[][] map = new char[park.length][park[0].length()];
        int startX = 0, startY = 0; 
        
        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[0].length(); j++){
                map[i][j] = park[i].charAt(j);
                if(map[i][j] == 'S'){
                    startX = j;
                    startY = i;
                }
            }
        }
        
        for(String r : routes){
            System.out.println(startY + " " + startX);
            int direction = 0;
            switch(r.charAt(0)){
                case 'N':
                    direction = 0;
                    break;
                case 'S':
                    direction = 1;
                    break;
                case 'W':
                    direction = 2;
                    break;
                case 'E':
                    direction = 3;
                    break;
            }
                        
            int newX = 0, newY = 0;
            boolean flag = true;
                        
            for(int i = 1; i <= r.charAt(2) - '0'; i++){
                newX = startX + (dx[direction] * i);
                newY = startY + (dy[direction] * i);
                
                if(newX < 0 || newY < 0 || newX >= park[0].length() || newY >= park.length){
                    flag = false;
                    break;
                }
                
                if(map[newY][newX] == 'X'){
                    flag = false;
                    break;
                }   
            }
            
            if(flag){
                startX = newX;
                startY = newY;
            }
        }
        
        int[] answer = {startY, startX};
        return answer;
    }
}