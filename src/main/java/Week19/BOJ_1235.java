package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1235 {
    static int N;
    static String[] students;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new String[N];

        for (int i = 0; i < N; i++) {
            students[i] = br.readLine();
        }

        len = students[0].length();

        for (int k = 1; k <= len; k++) {
            Set<String> uniqueNumbers = new HashSet<>();
            boolean isUnique = true;

            for (String student : students) {
                String sub = student.substring(len - k);
                if (!uniqueNumbers.add(sub)) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                System.out.println(k);
                return;
            }
        }
    }
}