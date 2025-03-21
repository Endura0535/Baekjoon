import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Set<Status> statusSet = new HashSet<>();
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, C));
        statusSet.add(new Status(0, 0, C));

        while (!queue.isEmpty()) {
            Status status = queue.poll();

            if (status.A != 0) {
                // A -> B
                int pour = Math.min(status.A, (B - status.B));
                Status status1 = new Status(status.A - pour, status.B + pour, status.C);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }

                // A -> C
                pour = Math.min(status.A, (C - status.C));
                status1 = new Status(status.A - pour, status.B, status.C + pour);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }
            }
            if (status.B != 0) {
                // B -> A
                int pour = Math.min(status.B, (A - status.A));
                Status status1 = new Status(status.A + pour, status.B - pour, status.C);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }

                // B -> C
                pour = Math.min(status.B, (C - status.C));
                status1 = new Status(status.A, status.B - pour, status.C + pour);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }
            }
            if (status.C != 0) {
                // C -> A
                int pour = Math.min(status.C, (A - status.A));
                Status status1 = new Status(status.A + pour, status.B, status.C - pour);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }

                // C -> B
                pour = Math.min(status.C, (B - status.B));
                status1 = new Status(status.A, status.B + pour, status.C - pour);
                if (!statusSet.contains(status1)) {
                    statusSet.add(status1);
                    queue.add(status1);
                }
            }
        }

        Set<Integer> answerSet = new HashSet<>();
        for (Status status : statusSet) {
            if (status.A == 0) {
                answerSet.add(status.C);
            }
        }

        List<Integer> answer = new ArrayList<>(answerSet);
        Collections.sort(answer);

        for (int num : answer) {
            System.out.print(num + " ");
        }
    }

    public static class Status {
        int A;
        int B;
        int C;

        public Status(int a, int b, int c) {
            A = a;
            B = b;
            C = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Status status = (Status) o;
            return A == status.A && B == status.B && C == status.C;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B, C);
        }
    }
}
