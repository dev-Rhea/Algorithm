import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<long[]> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            list.add(new long[]{x, y});
        }

        long sum = 0;
        
        for(int i = 0; i < N; i++) {
            int next = (i + 1) % N;
            
            long[] now = list.get(i);
            long[] nxt = list.get(next);
            
            sum += (now[0] * nxt[1] - nxt[0] * now[1]);
        }

        double area = Math.abs(sum) / 2.0;
        
        System.out.printf("%.1f%n", area);
    }
}