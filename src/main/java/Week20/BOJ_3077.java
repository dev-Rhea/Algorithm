package Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_3077 {
    static int N;
    static String[] correct;
    static String[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        correct = new String[N];
        answer = new String[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            correct[i] = st.nextToken();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            answer[i] = st.nextToken();
        }

        Map<String, Integer> correctIndex = new HashMap<>();
        Map<String, Integer> answerIndex = new HashMap<>();

        for (int i = 0; i < N; i++) {
            correctIndex.put(correct[i], i);
            answerIndex.put(answer[i], i);
        }

        int correctPairs = 0;
        int totalPairs = N * (N - 1) / 2;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (correctIndex.get(correct[i]) < correctIndex.get(correct[j]) &&
                        answerIndex.get(correct[i]) < answerIndex.get(correct[j])) {
                    correctPairs++;
                }
            }
        }

        System.out.println(correctPairs + "/" + totalPairs);
    }
}