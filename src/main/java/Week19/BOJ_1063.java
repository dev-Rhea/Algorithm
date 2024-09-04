package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1063 {
    static int kingX, kingY, stoneX, stoneY;
    static int N;
    static Map<String, int[]> moves = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Initialize movement directions
        moves.put("R", new int[]{0, 1});
        moves.put("L", new int[]{0, -1});
        moves.put("B", new int[]{1, 0});
        moves.put("T", new int[]{-1, 0});
        moves.put("RT", new int[]{-1, 1});
        moves.put("LT", new int[]{-1, -1});
        moves.put("RB", new int[]{1, 1});
        moves.put("LB", new int[]{1, -1});

        String[] input = br.readLine().split(" ");
        String kingPosition = input[0];
        String stonePosition = input[1];
        N = Integer.parseInt(input[2]);

        kingX = 8 - (kingPosition.charAt(1) - '0');
        kingY = kingPosition.charAt(0) - 'A';
        stoneX = 8 - (stonePosition.charAt(1) - '0');
        stoneY = stonePosition.charAt(0) - 'A';

        for(int i = 0; i < N; i++) {
            String move = br.readLine();
            int[] dir = moves.get(move);
            int nKingX = kingX + dir[0];
            int nKingY = kingY + dir[1];

            if (isValid(nKingX, nKingY)) {
                if (nKingX == stoneX && nKingY == stoneY) {
                    int nStoneX = stoneX + dir[0];
                    int nStoneY = stoneY + dir[1];
                    if (isValid(nStoneX, nStoneY)) {
                        kingX = nKingX;
                        kingY = nKingY;
                        stoneX = nStoneX;
                        stoneY = nStoneY;
                    }
                } else {
                    kingX = nKingX;
                    kingY = nKingY;
                }
            }
        }

        System.out.println((char) (kingY + 'A') + "" + (8 - kingX));
        System.out.println((char) (stoneY + 'A') + "" + (8 - stoneX));
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
