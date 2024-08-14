package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1141 {
    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        selectSort(words, N);

        List<String> list = checkSort(words, N);

        int ans = list.size();
        System.out.println(ans);
    }

    public static void selectSort(String[] array, int N){
        for(int i=0; i<N; i++){
            for(int j=1; j<N-i; j++){
                if(array[j-1].length() > array[j].length()){
                    String temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static List<String> checkSort(String[] array, int N){
        // 접두사X 집합인 부분집합
        List<String> list = new ArrayList<>();

        // 접두어x 집합일 경우 list에 추가
        for(int i=0; i<N; i++){
            Boolean result1 = true;
            for(int j=i+1; j<N; j++ ){
                if(array[j].indexOf(array[i]) == 0){
                    result1 = false;
                }
            }
            if(result1){
                list.add(array[i]);
            }
        }
        return list;
    }
}
