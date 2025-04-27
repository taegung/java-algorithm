import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class backjoon_2178 {

    static int [] dx={0,0,-1,1};
    static int [] dy={1,-1,0,0};
    static int [][]arr;
    static boolean[][] visted;
    static class Node{
        int x;
        int y;
        int cnt;

        public Node(int x,int y, int cnt){
            this.x=x;
            this.y=y;
            this.cnt =cnt;
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tk =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tk.nextToken());
        int M = Integer.parseInt(tk.nextToken());

         arr =new int[N][M];

        for(int i=0;i<N;i++){
            String input =br.readLine();
            for(int j=0;j<M;j++){
                arr[i][j]=Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        int ans=bfs(N,M);

        System.out.println(ans+1);
    }

    public static int bfs(int N, int M){

        int startx=0;
        int starty=0;
        visted = new boolean[N][M];
        Deque<Node> dq =new ArrayDeque<>();

        dq.offer(new Node(startx,starty,0));
        visted[startx][starty]=true;
        while(!dq.isEmpty()) {
            Node cur = dq.poll();
            int x = cur.x;
            int y = cur.y;
            int nodecnt = cur.cnt;
            for (int i = 0; i < 4; i++) {
                int gox = x + dx[i];
                int goy = y + dy[i];
                if (gox < M && gox >= 0 && goy < N && goy >= 0 &&
                        !visted[goy][gox] && arr[goy][gox] == 1) {
                    dq.offer(new Node(gox, goy, nodecnt + 1));
                    visted[goy][gox] = true;
                    if (gox == M - 1 && goy == N - 1) {
                        return nodecnt+1;
                    }
                }

            }
        }
        return startx;
    }


}
