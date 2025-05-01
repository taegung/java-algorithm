import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());


        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }


        Arrays.sort(arr);


        long left = 0;
        long right = arr[N - 1];
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long woodSum = 0;


            for (int i = 0; i < N; i++) {
                if (arr[i] > mid) {
                    woodSum += arr[i] - mid;
                }
            }

            if (woodSum >= M) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }


        System.out.println(result);
    }
}
