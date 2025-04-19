import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class backjoon_1012 {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int arr[][];
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};  //상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < Testcase; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(str.nextToken());//가로  10
            int N = Integer.parseInt(str.nextToken());//세로  6
            int vetnum = Integer.parseInt(str.nextToken()); //배추개수
            arr = new int[N][M];
            visited = new boolean[N][M];
            int ans = 0;

            for (int j = 0; j < N; j++) {  //배열 초기화
                Arrays.fill(arr[j], 0);
            }

            for (int j = 0; j < vetnum; j++) {  // 배추 심기
                StringTokenizer bf = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(bf.nextToken()); //1
                int y = Integer.parseInt(bf.nextToken());  //0
                arr[y][x] = 1;
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[j][k] == 1 && !visited[j][k]) {
                        ans++;
                        bfs(arr, j, k, N, M);
                    }

                }
            }

            System.out.println(ans);


        }

    }

    static void bfs(int[][] arr, int x, int y, int N, int M) {
        //가로 M
        Deque<Point> que = new LinkedList<>();
        visited[x][y] = true;
        que.add(new Point(x, y));

        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    que.add(new Point(nx, ny));
                }
            }
        }


    }
}
