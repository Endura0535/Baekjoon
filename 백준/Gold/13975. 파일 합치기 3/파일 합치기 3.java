import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> treeMap = new TreeMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                long n = Integer.parseInt(st.nextToken());
                if (treeMap.containsKey(n))
                    treeMap.put(n, treeMap.get(n) + 1);
                else
                    treeMap.put(n, 1);
            }

            long cost = 0;
            while (treeMap.size() != 1 || treeMap.firstEntry().getValue() != 1) {
                long key1 = treeMap.firstKey();
                long key2;
                if (treeMap.get(key1) > 1)
                    key2 = key1;
                else
                    key2 = treeMap.higherKey(key1);
                cost += key1 + key2;
                if (treeMap.get(key1) == 1)
                    treeMap.remove(key1);
                else
                    treeMap.put(key1, treeMap.get(key1) - 1);
                if (treeMap.get(key2) == 1)
                    treeMap.remove(key2);
                else
                    treeMap.put(key2, treeMap.get(key2) - 1);
                if (treeMap.containsKey(key1 + key2))
                    treeMap.put(key1 + key2, treeMap.get(key1 + key2) + 1);
                else
                    treeMap.put(key1 + key2, 1);
            }

            System.out.println(cost);

        }

    }

}