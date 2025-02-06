package implementation.BOJ_1919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[26];
        for(char c : br.readLine().toCharArray()) {
            ++arr[c - 'a'];
        }
        for(char c : br.readLine().toCharArray()) {
            --arr[c - 'a'];
        }
        System.out.println(Arrays.stream(arr).map(Math::abs).sum());
    }
}
