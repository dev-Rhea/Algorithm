package Week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ_20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        Map<String, Integer> words = new HashMap<>();

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            if(input.length() < M) {
                continue;
            }
            words.put(input, words.getOrDefault(input, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int cnt = words.get(b).compareTo(words.get(a));
            if(cnt == 0) {
                int len = Integer.compare(b.length(), a.length());
                if(len == 0) {
                    return a.compareTo(b);
                }
                return len;
            }
            return cnt;
        });

        pq.addAll(words.keySet());

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }

}
