
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon7576 {
    static int [][]map;
    static boolean [][]visit;
    static PriorityQueue<Point> pq;
    static int []dx={0,0,-1,1};
    static int []dy={1,-1,0,0};
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int day;
        public Point(int x,int y,int day){
            this.x=x;
            this.y=y;
            this.day=day;
        }
        @Override
        public int compareTo(Point point){
            return this.day-point.day;
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer bf = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(bf.nextToken());//6
        int N = Integer.parseInt(bf.nextToken());//4
        map =new int[N][M];
        visit =new boolean[N][M];
        pq =new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer bf1 =new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int num =Integer.parseInt((bf1.nextToken()));
                map[i][j]=num;
                if(num==1){
                       pq.offer(new Point(j,i,0));
                }
            }
        }

        int answer=bfs(N,M);

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==0){
                    answer=-1;
                    break;
                }
            }
        }


        System.out.println(answer);
    }

    static int bfs(int N,int M){
        int count=0;

        while(!pq.isEmpty()){
            Point cur =pq.poll();
                visit[cur.y][cur.x]=true;
            for(int i=0;i<4;i++){
                int nx=cur.x+dx[i];
                int ny=cur.y+dy[i];

                if(nx<0 || nx>=M || ny<0 || ny>=N || visit[ny][nx] || map[ny][nx]==-1){
                    continue;
                }

                if(map[ny][nx]==0){
                    map[ny][nx]=1;
                    pq.offer(new Point(nx,ny, cur.day+1));
                    count=cur.day+1;
                }
            }

        }
        return count;
    }

}
