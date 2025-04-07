package datastructure.deque.BOJ_1406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        for (char c : input.toCharArray()) left.addLast(c);

        for (int i = 0; i < M; i++) {
            String str = br.readLine();

            switch (str.charAt(0)) {
                case 'L':
                    if (!left.isEmpty()) right.addLast(left.removeLast());
                    break;
                case 'D':
                    if (!right.isEmpty()) left.addLast(right.removeLast());
                    break;
                case 'B':
                    if (!left.isEmpty()) left.removeLast();
                    break;
                case 'P':
                    char c = str.charAt(2);
                    left.addLast(c);
                    break;
            }
        }

        while (!left.isEmpty()) bw.write(left.removeFirst());
        while (!right.isEmpty()) bw.write(right.removeLast());
        bw.flush();
        bw.close();
    }
}
