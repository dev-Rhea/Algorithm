package dfs.BOJ_1039;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    
    static int K, len, ans;
    static Set<String>[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        len = N.length();

        visited = new HashSet[K+1];
        for(int i=0;i<=K;i++) {
            visited[i] = new HashSet<>();
        }

        ans = -1;
        dfs(N, 0);

        System.out.println(ans);
    }

    static void dfs(String n, int depth) {
        if(depth == K) {
            ans = Math.max(ans, Integer.parseInt(n));
            return;
        }

        if(visited[depth].contains(n)) return;
        visited[depth].add(n);

        char[] nums = n.toCharArray();

        for(int i=0;i<len;i++) {
            for(int j=i+1;j<len;j++) {
                if(i == 0 && nums[j] == '0') continue;

                swap(nums, i, j);
                dfs(new String(nums), depth+1);
                swap(nums, i, j);
            }
        }
    }

    static void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}