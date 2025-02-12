package recursion.BOJ_11729;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        System.out.println((int)Math.pow(2, N) - 1);
        hanoi(N, 1, 2, 3);
    }

    static void hanoi(int N, int start, int mid, int end) {
        if(N == 0) return;

        hanoi(N-1, start, end, mid); // 중간 기둥으로 원판 이동
        System.out.println(start + " " + end);
        hanoi(N-1, mid, start, end); // 3번째 기둥으로 원판 이동
    }
}