package backtracking.BOJ_16198;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> nums = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        energy(nums, 0);

        System.out.println(max);        
    }

    static void energy(List<Integer> nums, int sum) {

        if(nums.size() == 2) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=1;i<nums.size()-1;i++) {
            int temp = nums.get(i);
            int v = nums.get(i-1) * nums.get(i+1);

            nums.remove(i);
            energy(nums, sum + v);

            nums.add(i, temp);
        }
    }
}