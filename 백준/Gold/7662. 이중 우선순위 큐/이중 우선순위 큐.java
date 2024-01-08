import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int k = sc.nextInt();
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for(int j = 0; j < k; j++){
                String order = sc.next();
                if(order.equals("I")){
                    int n = sc.nextInt();
                    if(treeMap.containsKey(n))
                        treeMap.put(n, treeMap.get(n) + 1);
                    else
                        treeMap.put(n, 1);
                }else{
                    int x = sc.nextInt();
                    if(!treeMap.isEmpty()){
                        if(x == 1){
                            if(treeMap.lastEntry().getValue() == 1)
                                treeMap.pollLastEntry();
                            else{
                                treeMap.put(treeMap.lastKey(), treeMap.lastEntry().getValue()-1);
                            }
                        }else{
                            if(treeMap.firstEntry().getValue() == 1){
                                treeMap.pollFirstEntry();
                            }else{
                                treeMap.put(treeMap.firstKey(), treeMap.firstEntry().getValue()-1);
                            }
                        }
                    }
                }
            }
            if(treeMap.isEmpty()){
                System.out.println("EMPTY");
            } else {
                System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());
            }
        }
    }
}