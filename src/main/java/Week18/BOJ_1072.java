package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1072 {
    static int X, Y, Z;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        Z = (int) ((long) Y * 100 / X );

        if(Z >= 99) {
            System.out.println(-1);
            return;
        }

        int low = 0;
        int high = 1000000000;
        int answer = -1;

        while (low <= high) {
            int mid = (low+high) / 2;
            int nz = (int) ((long) (Y+mid) * 100/ (X+mid));

            if(nz > Z) {
                answer = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        System.out.println(answer);

    }
}
