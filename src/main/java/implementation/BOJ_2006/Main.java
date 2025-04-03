package implementation.BOJ_2006;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Player implements Comparable<Player> {
        int level;
        String name;
        boolean assigned = false; // 방 입장 여부 

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken()); 
        int m = Integer.parseInt(st.nextToken()); 

        Player[] players = new Player[p];
        
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            players[i] = new Player(level, name);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < p; i++) {
            if (players[i].assigned) continue;

            List<Player> room = new ArrayList<>();
            int minLevel = players[i].level - 10;
            int maxLevel = players[i].level + 10;

            for (int j = i; j < p && room.size() < m; j++) {
                if (!players[j].assigned && players[j].level >= minLevel && players[j].level <= maxLevel) {
                    players[j].assigned = true;
                    room.add(players[j]);
                }
            }

            Collections.sort(room);

            sb.append(room.size() == m ? "Started!" : "Waiting!").append("\n");
            for (Player player : room) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }

        System.out.print(sb);
    }
}
