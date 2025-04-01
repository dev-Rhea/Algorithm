package binarysearch.BOJ_12015;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int len = 1; // 가장 긴 부분 증가 수열 길이 변수 
        int[] list = new int[N];
        list[0] = nums[0]; // 시작 수 동일 

        // 두번째 원소 부터 탐색 
        for(int i=1;i<N;i++) {
            // 현재 숫자가 부분 수열의 마지막 숫자보다 크면 부분 수열 증가 
            if(list[len-1] < nums[i]) list[len++] = nums[i];
            else {
                // 그렇지 않은 경우, 현재숫자를 기존 부분 수열에서 교체 가능한 위치 탐색 
                int idx = search(list, nums[i], len - 1);
                list[idx] = nums[i]; // 교체 
            }
        }
        System.out.println(len);
    }

    private static int search(int[] list, int target, int end) {
        int start = 0;
        int ans = end + 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(list[mid] >= target) {
                ans = mid;
                end = mid - 1;
            }
            else start = mid + 1;
        }
        return ans;
    }
}