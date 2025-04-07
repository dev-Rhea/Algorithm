package datastructure.queue.BOJ_2696;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int M = Integer.parseInt(br.readLine());
            List<Integer> mid = new ArrayList<>();

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();

            int count = 0;

            int readCount = 0;
            while (readCount < M) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int n = Integer.parseInt(st.nextToken());
                    readCount++;

                    // 중앙값 후보 
                    if (left.isEmpty() || n <= left.peek()) {
                        left.offer(n);
                    } else {
                        // 중앙값 보다 큼 
                        right.offer(n);
                    }

                    // 균형 맞추기
                    if (left.size() > right.size() + 1) {
                        right.offer(left.poll());
                    } else if (right.size() > left.size()) {
                        left.offer(right.poll());
                    }

                    // 홀수 번째 입력일 때 중앙값 저장
                    if (readCount % 2 == 1) {
                        mid.add(left.peek());
                    }
                }
            }

            sb.append(mid.size()).append("\n");
            for (int i = 0; i < mid.size(); i++) {
                sb.append(mid.get(i)).append(" ");
                if ((i + 1) % 10 == 0) sb.append("\n");
            }
            if (mid.size() % 10 != 0) sb.append("\n");
        }

        System.out.print(sb);
    }
}
