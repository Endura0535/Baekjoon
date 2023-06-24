import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        for(int[] size : sizes){
            maxWidth = Math.max(maxWidth, Math.max(size[0], size[1]));
            maxHeight = Math.max(maxHeight, Math.min(size[0], size[1]));
        }
        int answer = maxWidth * maxHeight;
        return answer;
    }
}