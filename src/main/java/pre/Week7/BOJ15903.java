package pre.Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ15903 {
    static int n, m;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 자연수 n개 입력 받기
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        // m번의 연산 수행
        for(int i=0;i<m;i++){
            long temp = pq.poll(); // 2개를 poll 하여 합을 구한다음
            temp += pq.poll();

            pq.add(temp); // 다시 큐에 넣어줌
            pq.add(temp);
        }

        long hap = 0;
        while(!pq.isEmpty()){
            hap += pq.poll(); // 전체 합 구해줌
        }

        System.out.println(hap);
    }

}
