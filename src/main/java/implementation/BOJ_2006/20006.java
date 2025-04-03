package implementation.BOJ_2006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static class Node {
        int l;
        String n;

        public Node(int l, String n) {
            this.l = l;
            this.n = n;
        }
    }

    static class Room {
        int base;
        List<Node> players = new ArrayList<>();

        public Room(int base) {
            this.base = base;
        }

        public boolean Join(Node p, int max) {
            return players.size() < max && Math.abs(base - p.l) <= 10;
        }

        public void add(Node p) {
            players.add(p);
        }

        public boolean isFull(int max) {
            return players.size() == max;
        }

        public void Game(int max) {
            if (isFull(max)) System.out.println("Started!");
            else System.out.println("Waiting!");

            players.sort(Comparator.comparing(node -> node.n));
            for (Node node : players) {
                System.out.println(node.l + " " + node.n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            Node newPlayer = new Node(level, name);

            boolean isJoin = false;

            for (Room r : rooms) {
                if (r.Join(newPlayer, m)) {
                    r.add(newPlayer);
                    isJoin = true;
                    break;
                }
            }

            if (!isJoin) {
                Room newRoom = new Room(level);
                newRoom.add(newPlayer);
                rooms.add(newRoom);
            }
        }

        for (Room r : rooms) {
            r.Game(m);
        }
    }
}
