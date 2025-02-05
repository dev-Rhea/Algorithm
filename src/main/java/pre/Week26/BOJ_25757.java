package pre.Week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_25757 {
    static int N;
    static HashSet<String> names;
    static String game;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        game = st.nextToken();
        names = new HashSet<>();

        // 이름 입력

        for(int i=0;i<N;i++) {
            names.add(br.readLine());
        }

        if (game.equals("Y")) {
            System.out.println(names.size());
        }
        else if (game.equals("F")) {
            System.out.println(names.size() / 2);
        }
        else if (game.equals("O")) {
            System.out.println(names.size() / 3);
        }
    }

}
