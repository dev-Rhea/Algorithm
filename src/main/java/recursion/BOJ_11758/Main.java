package recursion.BOJ_11758;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(ccw(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
    }

    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int ans = (x2-x1) * (y3-y1) - (x3-x1) * (y2 - y1);
        return Integer.compare(ans, 0);
    }
}
