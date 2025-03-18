package twopointer.BOJ_1253;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int cnt = 0;

        for(int i=0;i<N;i++) {
            int left = 0;
            int right = N-1;

            while (true) { 
                // 현재 인덱스와 값이 같다면 건너뜀 
                if(i == left) left++;
                else if(right == i) right--;

                if(left >= right) break; // 두 포인터가 만나면 종료 

                if(nums[left] + nums[right] > nums[i]) right--; // 두 포인터 합이 현재 값보다 크다면 값을 줄이기 위해 right 감소 
                else if(nums[left] + nums[right] < nums[i]) left++; // 두 포인터 합이 현재 값보다 작다면 값을 늘리기 위해 left 증가 
                else {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}