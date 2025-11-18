package dfs.BOJ_16987;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Egg {
        int w, in;

        Egg(int in, int w) {
            this.in = in;
            this.w = w;
        }

        boolean isBroken() {
            return in <= 0;
        }
    }

    static int N;
    static Egg[] eggs;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        cnt = 0;

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(durability, weight);
        }

        dfs(0);

        System.out.println(cnt);
    }

    static void dfs(int idx) {
        if(idx == N) {
            int c = 0;
            for(int i=0;i<N;i++) {
                if(eggs[i].isBroken()) c++;
            }
            cnt = Math.max(c, cnt);
            return;
        }

        if(eggs[idx].isBroken() || unbroken() <= 1) {
            dfs(idx+1);
            return;
        }

        for(int i=0;i<N;i++) {
            if(i == idx || eggs[i].isBroken()) continue;

            eggs[idx].in -= eggs[i].w;
            eggs[i].in -= eggs[idx].w;

            dfs(idx+1);

            eggs[idx].in += eggs[i].w;
            eggs[i].in += eggs[idx].w;
        }
    }

    static int unbroken() {
        int cntU = 0;
        for(int i=0;i<N;i++) {
            if(!eggs[i].isBroken()) cntU++;
        }
        return cntU;
    }
}