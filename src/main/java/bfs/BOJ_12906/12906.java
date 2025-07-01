package bfs.BOJ_12906;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    
    static class Node {
        String[] move;
        int cnt;
        Node(String[] move, int cnt) {
            this.move = move;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> map = new HashMap<>();
        String[] stick = new String[3];

    
        for(int i=0;i<3;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            
            if(a > 0) {
                String str = st.nextToken();

                stick[i] = str;

                for(int j=0;j<str.length();j++) {
                    map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
                }
            }
            else stick[i] = "";
        }

        String[] moved = new String[3];
        moved[0] = "A".repeat(map.getOrDefault('A', 0));
        moved[1] = "B".repeat(map.getOrDefault('B', 0));
        moved[2] = "C".repeat(map.getOrDefault('C', 0));

        System.out.println(bfs(stick, moved));
    }

    static int bfs(String[] stick, String[] moved) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(stick, 0));

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(stick));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if(Arrays.equals(now.move, moved)) return now.cnt;

            for(int i=0;i<3;i++) {
                if(!now.move[i].isEmpty()) {
                    for(int j=0;j<3;j++) {
                        if(i != j) {
                            String[] temp = now.move.clone();

                            char top = temp[i].charAt(temp[i].length() - 1);
                            temp[i] = temp[i].substring(0, temp[i].length() - 1);

                            temp[j] += top;

                            if(visited.add(Arrays.toString(temp))) queue.add(new Node(temp, now.cnt+1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}