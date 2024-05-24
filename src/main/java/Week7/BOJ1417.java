package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ1417 {
    static int N, M;
    // 본인을 제외한 후보들의 득표수를 넣는다.
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()) - 1;
        M = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        int cnt = 0;
        while(!pq.isEmpty() && pq.peek() >= M){ // 본인의 득효수보다 크거나 같으면 감소시킨 후, 다시 큐에 넣음.
            ++M;
            pq.add(pq.poll() - 1);
            ++cnt;
        }
        System.out.println(cnt);

    }
}
