package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1655 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Queue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> queue2 = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        // 불러야할 정수 입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(queue1.size() == queue2.size()) queue1.add(num);
            else queue2.add(num);

            if(queue1.size() > 0 && queue2.size() > 0 && queue1.peek() > queue2.peek()) {
                int tmp1 = queue1.poll();
                int tmp2 = queue2.poll();
                queue1.add(tmp2);
                queue2.add(tmp1);
            }

            sb.append(queue1.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
