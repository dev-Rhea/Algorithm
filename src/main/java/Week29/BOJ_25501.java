package Week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class BOJ_25501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++) {
            String S = br.readLine();
            recursion(S, 0, S.length() - 1);
        }
    }

    private static void recursion(String S, int start, int end) {
        if(start >= end) {
            System.out.println(1 + " " + (start + 1));
        }
        else if(S.charAt(start) != S.charAt(end)) {
            System.out.println(0 + " " + (start + 1));
        }
        else {
            recursion(S, start + 1, end - 1);
        }
    }
}
