package pre.Week22;

import java.io.*;

public class BOJ_31799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // 학기의 수
        char[] str = br.readLine().toCharArray(); // 평어

        boolean lastCheck = str[str.length - 1] == 'A' || str[str.length - 1] == 'B' || str[str.length - 1] == 'C' ? true : false; // 마지막이 +, 0, - 가 아니라면 true
        String before = " "; // 이전 학기 평어
        String nowStr = " "; // 현재 참조할 평어
        char now = ' '; // A, B, C를 판단할 변수
        char next = ' '; // +. -. 0를 판단할 변수

        for (int i = 0; i < str.length - 1; i++) {
            now = str[i];
            next = str[i + 1];

            if (next == '+' || next == '-') {
                nowStr = String.valueOf(now) + String.valueOf(next);
                i++;
            } else if (next == '0') {
                nowStr = String.valueOf(now);
                i++;
            } else {
                nowStr = String.valueOf(now);

                if (i == str.length - 2) {
                    lastCheck = true;
                }
            }

            sb.append(change(nowStr, before));
            before = nowStr; // 현재 평어를 이전 평어로 업데이트
        }

        if (lastCheck) { // 마지막 평어가 +, -, 0가 아니라면 한번 더 변환
            sb.append(change(String.valueOf(str[str.length - 1]), before));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 새로운 평어로 변환해줄 함수.
    private static char change(String s, String b) {
        if (s.charAt(0) == 'C') {
            return 'B';
        } else if (s.equals("B") || s.equals("B-")) {
            if (b.charAt(0) == 'C' || b.equals(" ")) {
                return 'D';
            } else {
                return 'B';
            }
        } else if (s.equals("A-") || s.equals("B+")) {
            if (b.equals("B+") || b.charAt(0) == 'A') {
                return 'D';
            } else {
                return 'P';
            }
        } else if (s.equals("A")) {
            if (b.equals("A+") || b.equals("A")) {
                return 'P';
            } else {
                return 'E';
            }
        } else {
            return 'E';
        }
    }
}



