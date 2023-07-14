import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> sumList = new HashSet<>();
        for(int i = 1; i <= elements.length; i++){  // i : 몇 개의 원소를 더할지
            for(int j = 0; j < elements.length; j++){    // j : 몇 번째 index부터 시작할지
                int sum = 0;
                for(int cnt = 0; cnt < i; cnt++){   // cnt : 몇 개 더했는지 세기
                    if(j + cnt  >= elements.length)
                        sum += elements[j+cnt-elements.length];
                    else
                        sum += elements[j+cnt];
                }
                sumList.add(sum);
            }
        }
                
        return sumList.size();
    }
}