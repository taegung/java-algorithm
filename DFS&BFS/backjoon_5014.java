import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.IOException;

public class backjoon_5014{

    static class Floor {
        int x;
        int count;
        public Floor(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken()); // 건물 총 층수
        int start = Integer.parseInt(st.nextToken()); // 시작 위치
        int end = Integer.parseInt(st.nextToken()); // 목표 위치
        int up = Integer.parseInt(st.nextToken()); // 위로 이동
        int down = Integer.parseInt(st.nextToken()); // 아래로 이동

        //  시작과 목표가 같으면 바로 0 출력
        if (start == end) {
            System.out.println(0);
            return;
        }

        visited = new boolean[F + 1];
        int[] move = new int[]{up, -down};

        Deque<Floor> q = new LinkedList<>();
        q.offer(new Floor(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Floor f = q.poll();

            for (int i = 0; i < 2; i++) {
                int cur = f.x + move[i];
                if (cur > 0 && cur <= F && !visited[cur]) {
                    if (cur == end) {
                        System.out.println(f.count + 1);
                        return;
                    }
                    visited[cur] = true;
                    q.offer(new Floor(cur, f.count + 1));
                }
            }
        }

        System.out.println("use the stairs");
    }
}
