package Week29;

import java.util.Scanner;

public class BOJ_27433 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long m = 1;

        if (N == 0) {
            m = 1;
        } else {
            for (int i = N; i >= 1; i--) {
                m *= i;
            }
        }
        System.out.println(m);
    }
}
