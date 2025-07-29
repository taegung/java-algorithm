import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon16236 {
    static int map[][];
    static PriorityQueue<Shark> dq;
    static PriorityQueue<Shark> result;
    static int N;
    static boolean visit[][];
    static int[]dx ={0,-1,1,0};
    static int[]dy={-1,0,0,1};
    static int a=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br  =new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        map =new int[N][N];
        visit =new boolean[N][N];
        dq=new PriorityQueue<>();
        result=new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==9){
                    dq.add(new Shark(j,i,2,0,0));
                    map[i][j]=0;
                }
            }
        }

        while(true){
            Shark s1 = dq.poll();
            if(s1.size==-1){
                System.out.println(a);
                return;
            }
            int flag=0;
            int zerocount=0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]==0){
                        zerocount++;
                    }else if(map[i][j]<s1.size){
                        flag=1;
                    }
                }
            }

            if(flag==0 || zerocount==N*N ){

                System.out.println(s1.clock);
                return;
            }
            int b;
            b=bfs(s1.x,s1.y, s1.size, s1.eat, s1.clock);
            if(b!=-1){
                a=b;
            }
//            for(int i=0;i<N;i++){
//                System.out.println();
//                for(int j=0;j<N;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//            }

            for(int i=0;i<N;i++){
                Arrays.fill(visit[i],false);
            }

        }




    }

    static int bfs(int x, int y, int size, int eat, int clock){

        dq.offer(new Shark(x,y,size,eat,clock));
        visit[y][x]=true;
        int circle=0;
        while(!dq.isEmpty()){
            int flag=0;
            Shark cur = dq.poll();
            //System.out.println(""+cur.x+" "+cur.y+" "+cur.size+" "+cur.eat+" "+cur.clock);
            for(int i=0;i<4;i++){
                int nx =cur.x+dx[i];
                int ny =cur.y+dy[i];

                if(nx<0 || nx>=N ||ny<0 ||ny>=N || visit[ny][nx] ||cur.size<map[ny][nx]){
                    continue;
                }

                if(map[ny][nx]==cur.size || map[ny][nx]==0){
                        dq.offer(new Shark(nx,ny,cur.size,cur.eat,cur.clock+1));
                        visit[ny][nx]=true;
                }else if(map[ny][nx]<cur.size){ //상어가 먹어

                        if(cur.size==cur.eat+1){
                            cur.size=cur.size+1;
                            cur.eat=0;
                        }else{
                            cur.eat=cur.eat+1;
                        }

                        if(circle==0) { // 처음일떄
                            result.offer(new Shark(nx, ny, cur.size, cur.eat, cur.clock + 1));
                            circle = cur.clock + 1;
                            visit[ny][nx]=true;
                        }else if(circle>cur.clock){
                            result.offer(new Shark(nx, ny, cur.size, cur.eat, cur.clock + 1));
                            visit[ny][nx]=true;
                        }else{
                            dq.clear();
                            if (!result.isEmpty()) {
                                Shark s = result.poll();
                                dq.offer(new Shark(s.x, s.y, s.size, s.eat, s.clock));
                                map[s.y][s.x] = 0;
                                result.clear();
                                return s.clock;
                            }

                        }
                        //dq.clear();
//                        dq.offer(new Shark(nx,ny,cur.size,cur.eat,cur.clock+1));
//
//                        System.out.println();
//                        System.out.println((++c)+" "+ ny+","+nx +"eat:"+cur.eat+"size: "+ cur.size);
//                        map[ny][nx]=0;
                        //return;
                }

            }
        }

        if (!result.isEmpty()) {
            Shark s=result.poll();
            dq.offer(new Shark(s.x,s.y,s.size,s.eat,s.clock));
            map[s.y][s.x]=0;
            result.clear();
            return s.clock;
        }
        dq.offer(new Shark(0,0,-1,-1,-1));
        return -1;
    }




    static class Shark implements Comparable<Shark>{
        int x;
        int y;
        int size;
        int eat;
        int clock;

        public Shark(int x,int y,int size,int eat,int clock){
            this.x=x;
            this.y=y;
            this.size=size;
            this.eat=eat;
            this.clock=clock;
        }
        @Override
        public int compareTo(Shark shark){
            if(this.clock!=shark.clock){
                return this.clock-shark.clock;
            }
            if(this.y!=shark.y){
                return this.y-shark.y;
            }
            return this.x-shark.x;
        }

    }
}
