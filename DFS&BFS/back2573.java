import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class back2573{

    static int dx[] = {0, 0, -1, 1};  // 상, 하, 좌, 우
    static int dy[] = {1, -1, 0, 0};
    static boolean visited[][];
    static int arr[][];
    static int karr[] = new int[3];  // 0이 아닌 개수, x축, y축

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer df = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(df.nextToken());
        int M = Integer.parseInt(df.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int w = 0; w < M; w++) {
                arr[i][w] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        while (true) {
            int groupCount = 0;
            for (int i = 0; i < N; i++) Arrays.fill(visited[i], false);

            // 빙산 덩어리 개수 체크
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        bfs(j, i, N, M);  // 덩어리 하나 처리하면서 높이도 갱신
                        groupCount++;
                    }
                }
            }

            if (groupCount == 0) {
                System.out.println(0);
                break;
            }

            if (groupCount >= 2) {
                System.out.println(result);
                break;
            }

            result++;
        }
    }

    static void bfs(int x, int y, int N, int M) {
        int[][] newarr = new int[N][M];
        for (int i = 0; i < N; i++) newarr[i] = arr[i].clone();

        Deque<Point> dq = new LinkedList<>();
        visited[y][x] = true;
        dq.offer(new Point(x, y));

        while (!dq.isEmpty()) {
            Point p = dq.poll();
            int fx = p.x;
            int fy = p.y;

            int minus = fourdir(fx, fy, N, M);
            if (arr[fy][fx] > minus) {
                newarr[fy][fx] = arr[fy][fx] - minus;
            } else {
                newarr[fy][fx] = 0;
            }

            for (int i = 0; i < 4; i++) {
                int x1 = fx + dx[i];
                int y1 = fy + dy[i];
                if (x1 >= 0 && x1 < M && y1 >= 0 && y1 < N && arr[y1][x1] != 0 && !visited[y1][x1]) {
                    visited[y1][x1] = true;
                    dq.offer(new Point(x1, y1));
                }
            }
        }

        for (int i = 0; i < N; i++) arr[i] = newarr[i].clone();
    }

    static int fourdir(int x, int y, int N, int M) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int x1 = x + dx[i];
            int y1 = y + dy[i];
            if (x1 >= 0 && x1 < M && y1 >= 0 && y1 < N && arr[y1][x1] == 0) {
                count++;
            }
        }
        return count;
    }
}
