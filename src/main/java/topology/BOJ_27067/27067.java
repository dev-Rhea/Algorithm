import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static class Data {
        int n, v;
        Data(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] data = new ArrayList[3];

        for(int i=0;i<3;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i] = new ArrayList<>();

            for(int j=0;j<N;j++) {
                data[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        Queue<Data> queue = new PriorityQueue<>((o1, o2) -> (o1.v - o2.v));
        for(int i=0;i<N;i++) {
            // 각 데이터 배열에서 위치들
            int d0 = i + 1;
            int d1 = data[1].indexOf(data[0].get(i)) + 1;
            int d2 = data[2].indexOf(data[0].get(i)) + 1;
            // 세 위치의 중앙 값을 기준으로 거리의 합 계산
            // degree가 작을 수록 해당 원소들의 위치가 비슷함
            // degree가 클수록 해당 원소들의 위치 차이가 큼 
            int degree = n + d1 + d2 - Math.min(n, Math.min(d1, d2));

            queue.add(new Data(data[0].get(i), degree));
        }

        while(!queue.isEmpty()) {
            System.out.print(queue.poll().n + " ");
        }

    }
}