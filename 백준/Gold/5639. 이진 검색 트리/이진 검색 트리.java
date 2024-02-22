import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static Stack<Integer> stack;

    public static class Node {

        int n;
        Node left = null, right = null;

        public Node(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return "n: " + n + " left: " + left + " right: " + right;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Node root = null;
        stack = new Stack<>();
        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty())
                break;

            int n = Integer.parseInt(input);
            if (root == null)
                root = new Node(n);
            else {
                Node curNode = root;
                Node parentNode = null;

                while (curNode != null) {
                    parentNode = curNode;
                    if (curNode.n < n)
                        curNode = curNode.right;
                    else
                        curNode = curNode.left;
                }

                Node newNode = new Node(n);
                if (parentNode.n < n)
                    parentNode.right = newNode;
                else
                    parentNode.left = newNode;
            }

        }

        post(root);
        while (!stack.isEmpty())
            System.out.println(stack.pop());

    }

    public static void post(Node node) {
        stack.add(node.n);
        if (node.right != null)
            post(node.right);
        if (node.left != null)
            post(node.left);
    }

}