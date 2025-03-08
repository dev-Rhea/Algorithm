package dfs.BOJ_3584.BOJ_2668;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

    static int[] nums;
    static boolean[] visited;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        list = new ArrayList<>();
        
        for(int i=1;i<=N;i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(int n : list) System.out.println(n);
    }

    static void dfs(int idx, int target) {
        if(nums[idx] == target) list.add(target);

        if(!visited[nums[idx]]) {
            visited[nums[idx]] = true;
            dfs(nums[idx], target);
            visited[nums[idx]] = false;
        }
    }
}