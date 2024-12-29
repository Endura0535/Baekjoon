import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> chulsuMap = new HashMap<>();
        Set<Integer> chulsuSet = new HashSet<>();
        Set<Integer> broSet = new HashSet<>();
        for (int t : topping) {
            if (chulsuMap.containsKey(t))
                chulsuMap.put(t, chulsuMap.get(t) + 1);
            else chulsuMap.put(t, 1);
            chulsuSet.add(t);
        }

        int chulsuTopping = chulsuSet.size();
        int broTopping = 0;
        int answer = 0;
        for (int i = topping.length - 1; i >= 0; i--) {
            int t = topping[i];
            if (chulsuMap.get(t) == 1) {
                chulsuSet.remove(t);
                chulsuTopping--;
            }
            if (!broSet.contains(t)) {
                broSet.add(t);
                broTopping++;
            }
            chulsuMap.put(t, chulsuMap.get(t) - 1);
            if (chulsuTopping == broTopping)
                answer++;
        }

        return answer;
    }
}