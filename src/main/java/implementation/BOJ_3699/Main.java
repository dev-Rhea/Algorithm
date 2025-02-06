package implementation.BOJ_3699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {


        int T = Integer.parseInt(br.readLine());


        for(int k=0;k<T;k++) {
            FindCar();
        }
    }

    private static void FindCar() throws IOException {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;

        for(int i=0;i<h;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<l;j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) continue;
                map.put(num, i*100+j);
                cnt++;
            }
        }

        int[] belt = new int[h+1];
        for(int i=0;i<h;i++) {
            belt[i] = 1;
        }

        int time = 0;
        for(int i=0;i<cnt;i++) {
            int ti = map.get(i) / 100;
            int tj = map.get(i) % 100;

            time += (ti - 1) * 10 * 2;
            time += Math.min(Math.abs(belt[ti]-tj), l - Math.abs(belt[ti]-tj)) * 5;

            belt[ti] = tj;
        }
        System.out.println(time);
    }
}
