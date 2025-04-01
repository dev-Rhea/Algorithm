package bruteforce.BOJ_14658;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Node> stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new Node(x, y));
        }

        int cnt = 0;

        // 모든 별들의 좌표를 기준으로 가장 왼쪽아래 꼭짓점을 (s1.x, s2.y)로 설정
        // 해당 위치에서 L*L 크기의 정사각형 안에 있는 별의 수 계산 
        for(Node s1 : stars) {
            for(Node s2 : stars) {
                cnt = Math.max(cnt, boundary(s1.x, s2.y, L));
            }
        }

        // 트램펄린으로 튕겨내지 못한 별의 갯수 
        System.out.println(K - cnt);
    }

    private static int boundary(int i, int j, int l) {
        int ans = 0;

        // (i, j)를 왼쪽 아래 꼭짓점으로 두고, l*l 크기의 사각형 내부의 별의 갯수 확인 
        for(Node s : stars) {
            if(s.x >= i && s.x <= i+l && s.y >=j && s.y <= j+l) ans++;
        }

        return ans;
    }
}