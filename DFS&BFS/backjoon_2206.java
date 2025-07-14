import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class backjoon_2206 {
    static int map[][];
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    static boolean[][][] visited;

    static class Point {
        int x;
        int y;
        int flag;   // 0이면 아직 벽을 안 부쉈고, 1이면 벽을 부쉈음
        int count;

        public Point(int x, int y, int flag, int count) {
            this.x = x;
            this.y = y;
            this.flag = flag;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // ★ 여기만 바뀜

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int w = 0; w < M; w++) {
                map[i][w] = str.charAt(w) - '0';
            }
        }

        int result = bfs(0, 0, N, M);
        System.out.println(result);
    }

    static int bfs(int x, int y, int N, int M) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(x, y, 0, 1));
        visited[y][x][0] = true;

        while (!dq.isEmpty()) {
            Point cur = dq.poll();

            int nx = cur.x;
            int ny = cur.y;
            int nflag = cur.flag;

            if (nx == M - 1 && ny == N - 1) {
                return cur.count;
            }

            for (int i = 0; i < 4; i++) {
                int gox = nx + dx[i];
                int goy = ny + dy[i];

                if (gox >= 0 && gox < M && goy >= 0 && goy < N) {
                    // 이동할 수 있는 칸일 경우
                    if (map[goy][gox] == 0 && !visited[goy][gox][nflag]) {
                        visited[goy][gox][nflag] = true;
                        dq.offer(new Point(gox, goy, nflag, cur.count + 1));
                    }
                    // 벽을 만났고 아직 벽을 안 부쉈을 경우
                    else if (map[goy][gox] == 1 && nflag == 0 && !visited[goy][gox][1]) {
                        visited[goy][gox][1] = true;
                        dq.offer(new Point(gox, goy, 1, cur.count + 1));
                    }
                }
            }
        }

        return -1;
    }
}
