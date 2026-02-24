package 문자열.BOJ_1148;
import java.io.*;
import java.util.*;

class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<int[]> dict = new ArrayList<>();
        List<Integer> dictMask = new ArrayList<>();

        String input;
        while (!(input = br.readLine()).equals("-")) {
            int[] freq = new int[26];
            int mask = 0;
            for (char c : input.toCharArray()) {
                freq[c - 'A']++;
                mask |= (1 << (c - 'A'));
            }
            dict.add(freq);
            dictMask.add(mask);
        }

        String st;
        while (!(st = br.readLine()).equals("#")) {
            char[] temp = st.toCharArray();
            finder(temp, dict, dictMask);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void finder(char[] ch, List<int[]> dict, List<Integer> dictMask) {
        int[] boardCnt = new int[26];
        int boardMask = 0;
        for (char c : ch) {
            boardCnt[c - 'A']++;
            boardMask |= (1 << (c - 'A'));
        }

        Map<Character, Integer> ans = new TreeMap<>();
        for (char c : ch) {
            if (ans.containsKey(c)) continue;
            int centerBit = (1 << (c - 'A'));
            int cc = 0;
            for (int i = 0; i < dict.size(); i++) {
                int wMask = dictMask.get(i);
                if ((wMask & boardMask) != wMask) continue;
                if ((wMask & centerBit) == 0) continue; 

                int[] wFreq = dict.get(i);
                boolean ok = true;
                for (int j = 0; j < 26; j++) {
                    if (wFreq[j] > boardCnt[j]) { ok = false; break; }
                }
                if (ok) cc++;
            }
            ans.put(c, cc);
        }

        int min = Collections.min(ans.values());
        int max = Collections.max(ans.values());

        for (Map.Entry<Character, Integer> e : ans.entrySet()) {
            if (e.getValue() == min) sb.append(e.getKey());
        }
        sb.append(" ").append(min).append(" ");

        for (Map.Entry<Character, Integer> e : ans.entrySet()) {
            if (e.getValue() == max) sb.append(e.getKey());
        }
        sb.append(" ").append(max);
    }
}