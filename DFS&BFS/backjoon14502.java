
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon14502 {
    static int [][] map;
    static int [][] newmap;
    static boolean[][] visit;
    static int [][] zero;
    static int [] arr =new int[3];
    static int [] number;
    static int dx[]={0,0,-1,1};
    static int dy[]={1,-1,0,0};
    static int max=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());//4 세로
        int M=Integer.parseInt(st.nextToken());//6 가로

        map =new int[N][M];

        zero=new int[N*M][2];
        int zerocnt=0;
        for(int i=0;i<N;i++){
            StringTokenizer st1 =new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int num=Integer.parseInt(st1.nextToken());
                map[i][j]=num;
                if(num==0){
                    zero[zerocnt][0]=j; //x죄표
                    zero[zerocnt][1]=i; //y좌표
                    zerocnt++;
                }
            }
        }
         number =new int[zerocnt];
        for(int i=0;i<zerocnt;i++){
            number[i]=i;
        }
        combine(0,0,zerocnt,3,N,M);

        System.out.println(max);

    }


    static void combine(int idx,int start,int zerocnt,int r,int N,int M){
        if(idx==r){
            int result= bfs(arr[0],arr[1],arr[2],zerocnt,N,M);
            if(max<result){
                if(result!=0){
                    max=result;
//                    System.out.println("----------"+max+"-----------");
//                    System.out.println(zero[arr[0]][0]+" "+zero[arr[0]][1]);
//                    System.out.println(zero[arr[1]][0]+" "+zero[arr[1]][1]);
//                    System.out.println(zero[arr[2]][0]+" "+zero[arr[2]][1]);
                }

            }
            return;
        }

        for(int i=start;i<zerocnt;i++){
            arr[idx]=number[i];
            combine(idx+1,i+1,zerocnt,r,N,M);
        }
    }

    static int bfs(int n1, int n2, int n3, int zerocnt, int N, int M) {
        newmap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newmap[i] = map[i].clone();
        }

        visit = new boolean[N][M];

        // 벽 세우기
        newmap[zero[n1][1]][zero[n1][0]] = 1;
        newmap[zero[n2][1]][zero[n2][0]] = 1;
        newmap[zero[n3][1]][zero[n3][0]] = 1;


        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newmap[i][j] == 2) {
                    dq.offer(new int[]{i, j}); // i = row = y, j = column = x
                    visit[i][j] = true;
                }
            }
        }

        // BFS
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (visit[ny][nx] || newmap[ny][nx] != 0) continue;

                visit[ny][nx] = true;
                newmap[ny][nx] = 2;
                dq.offer(new int[]{ny, nx});
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newmap[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }




}
