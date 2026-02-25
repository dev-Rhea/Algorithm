package greedy.BOJ_4716;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static class Team {
        int cnt, da, db;
        Team(int cnt, int da, int db) {
            this.cnt = cnt;
            this.da = da;
            this.db = db;
        }
    }

    static int N, A, B;
    static Team[] teams;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (N == 0 && A == 0 && B == 0) break;

            teams = new Team[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int dA = Integer.parseInt(st.nextToken());
                int dB = Integer.parseInt(st.nextToken());
                teams[i] = new Team(K, dA, dB);
            }

            System.out.println(find());
        }
    }

    static long find() {
        Arrays.sort(teams, (a, b) -> Math.abs(b.da - b.db) - Math.abs(a.da - a.db));
        long total = 0;
        int sa = A;
        int sb = B;
        
        for(Team t : teams) {
            if(t.da <= t.db) {
                int fa = Math.min(sa, t.cnt);
                int fb = t.cnt - fa;

                total += (long) (fa * t.da);
                total += (long) (fb * t.db);

                sa -= fa;
                sb -= fb;
            }
            else {
                int fb = Math.min(sb, t.cnt);
                int fa = t.cnt - fb;

                total += (long) (fb * t.db);
                total += (long) (fa * t.da);

                sb -= fb;
                sa -= fa;
            }
        }
        return total;
    }
}

