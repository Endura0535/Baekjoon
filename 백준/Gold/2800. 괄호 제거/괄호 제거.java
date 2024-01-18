import java.util.*;

public class Main {

    static char[] inputArr;
    static char[] startArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    static char[] endArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static List<String> answerList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int idx = 0;
        inputArr = input.toCharArray();
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        for (char c : inputArr) {
            if (c == '(') {
                inputArr[cnt] = startArr[idx];
                stack.add(idx++);
            }
            if (c == ')') {
                inputArr[cnt] = endArr[stack.pop()];
            }
            cnt++;
        }

        subset(idx, 0, new ArrayList<>());

        Collections.sort(answerList);
        for (String answer : answerList) {
            System.out.println(answer);
        }

    }

    public static void subset(int n, int cnt, List<Integer> list) {
        if (cnt == n) {
            if (list.size() == n) return;
            String answer = new String(inputArr);
            for (char c : inputArr) {
                int index = new String(startArr).indexOf(c);
                if (index != -1) {
                    if (!list.contains(index))
                        answer = answer.replace(String.valueOf(c), "");
                    else
                        answer = answer.replace(String.valueOf(c), "(");
                }
                index = new String(endArr).indexOf(c);
                if (index != -1) {
                    if (!list.contains(index))
                        answer = answer.replace(String.valueOf(c), "");
                    else
                        answer = answer.replace(String.valueOf(c), ")");
                }
            }
            if (!answerList.contains(answer))
                answerList.add(answer);
            return;
        }
        subset(n, cnt + 1, list);
        List<Integer> list2 = new ArrayList<>(list);
        list2.add(cnt);
        subset(n, cnt + 1, list2);
    }
}