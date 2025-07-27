import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon2636 {
    static int dx[] ={0,0,1,-1};
    static int dy[] ={1,-1,0,0};
    static boolean visit[][];
    static int map[][];
    static int result;
    static List<Point> newmap;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken());  //세로
        int M= Integer.parseInt(st.nextToken());  //가로

        visit =new boolean[N][M];
        map= new int [N][M];
        for(int i=0;i<N;i++){   //입력
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int num=Integer.parseInt(st1.nextToken());
                map[i][j]=num;
                if(map[i][j]==1){
                    result++;
                }
            }
        }

        int clock=0;
        while(true) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(visit[i], false);
            }
            bfs(0,0,N,M);
//            System.out.println();
//            for(int i=0;i<N;i++){   //입력
//
//                for(int j=0;j<M;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }

            if (newmap.isEmpty()) {
                System.out.println(clock);
                System.out.println(result);
                break;
            }
            clock++;
            result=newmap.size();

            for(Point p :newmap){
                map[p.y][p.x] = 0;
            }

        }


    }

    static void bfs(int x,int y,int N,int M){

      newmap = new ArrayList<>();

        Deque<Point> dq =new ArrayDeque<>();
        dq.offer(new Point(x,y));

        while(!dq.isEmpty()){
            Point cur =dq.poll();
            visit[cur.y][cur.x] = true;
            for(int i=0;i<4;i++){
                int nx=cur.x+dx[i];
                int ny=cur.y+dy[i];

                if(nx<0 || nx>M-1 || ny<0 ||ny>N-1 ||visit[ny][nx]){
                    continue;
                }

                if(map[ny][nx]==1){
                    newmap.add(new Point(nx,ny));
                    visit[ny][nx]=true;
                }else{
                    dq.offer(new Point(nx,ny));
                     visit[ny][nx]=true;
                }
            }
        }


    }

    static class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }


}
