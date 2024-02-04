import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        int[] ship = new int[N];
        List<Integer> shipList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            treeMap.put(s, new ArrayList<>());
            shipList.add(s);
        }
        Collections.sort(shipList);
        for (int i = 0; i < N; i++) {
            ship[i] = shipList.get(i);
        }

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int key = treeMap.lastKey();
            if (x > key) {
                System.out.println(-1);
                return;
            }
            boolean insertFlag = false;
            while (key != treeMap.firstKey()) {
                int nextKey = treeMap.lowerKey(key);
                if (nextKey < x && x <= key) {
                    treeMap.get(key).add(x);
                    insertFlag = true;
                    key = nextKey;
                    continue;
                }
                key = nextKey;
            }

            if (!insertFlag) {
                treeMap.firstEntry().getValue().add(x);
            }
        }

        int answer = 0;
        while (M > 0) {
            answer++;
            for (int i = N - 1; i >= 0; i--) {
                for (int j = i; j >= 0; j--) {
                    if (!treeMap.get(ship[j]).isEmpty()) {
                        treeMap.get(ship[j]).remove(0);
                        M--;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);

    }

}