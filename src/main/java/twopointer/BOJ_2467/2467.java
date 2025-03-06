package twopointer.BOJ_2467;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  int n;
  int[] arr;
  int min = Integer.MAX_VALUE;
  int[] answer = new int[2];

  public static void main(String[] args) throws Exception {
    new Main().solution();
  }

  public void solution() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    arr = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int left = 0;
    int right = n - 1;
    while (left < right) {
      int sum = Math.abs(arr[left] + arr[right]);

      if (sum < min) {
        min = sum;
        answer[0] = arr[left];
        answer[1] = arr[right];
      }

      if (arr[left] + arr[right] >= 0) {
        right--;
      } else {
        left++;
      }
    }

    for (int a : answer) {
      System.out.print(a + " ");
    }
  }
}