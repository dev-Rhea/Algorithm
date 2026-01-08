package implementation.BOJ_16434;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static class Room {
        int type, a, h;
        Room(int type, int a, int h) {
            this.type = type;
            this.a = a;
            this.h = h;
        }
    }

    static int N;
    static long initAttack;
    static Room[] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        initAttack = Long.parseLong(st.nextToken());
        rooms = new Room[N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            rooms[i] = new Room(t, a, h);
        }

        long left = 1;
        long right = Long.MAX_VALUE / 2;
        long answer = right;

        while(left <= right) {
            long mid = (left + right) / 2;

            if(clear(mid)) {
                answer = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        System.out.println(answer);
    }

    static boolean clear(long max) {
        long now = max;
        long attack = initAttack;

        for(Room r : rooms) {
            if(r.type == 1) {
                long monsterA = r.a;
                long monsterHp = r.h;

                long heroTurn = (monsterHp + attack - 1) / attack;
                long monTurn = heroTurn - 1;

                long damage = monsterA * monTurn;

                now -= damage;

                if(now <= 0) return false;
            }
            else {
                attack += r.a;
                now = Math.min(now+r.h, max);
            }
        }
        return true;
    }
}