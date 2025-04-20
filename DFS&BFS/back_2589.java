import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back_2589 {

    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static boolean[][] visited;

    static class Land{
        int x;
        int y;
        public Land(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static class Searchland{
        int x;
        int y;
        int count;
        public Searchland(int x, int y,int count) {
            this.x=x;
            this.y=y;
            this.count=count;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer bf =new StringTokenizer(br.readLine());

        int height = Integer.parseInt(bf.nextToken());//5
        int width = Integer.parseInt(bf.nextToken());//7


        String [][] map = new String[height][width];
        ArrayDeque<Land> lands = new ArrayDeque<>();// 땅위치 저장한 배열

        for(int i=0;i<height;i++){   // 입력값 맵에 저장
            String str=br.readLine();
            String st[] = str.split("");
            for(int j=0;j<width;j++){
                map[i][j]=st[j];
                if(st[j].equals("L")){ //땅인 인덱스 값 저장
                    lands.add(new Land(i,j));
                }
            }
        }
        int [] maxarr=new int[lands.size()];


        visited=new boolean[height][width];

        ArrayDeque<Searchland> bfsland = new ArrayDeque<>(); //bfs탐색하기위한 큐
        int index =0; // 각땅위 최대값 담을 배열의 인덱스

        while(!lands.isEmpty()){  // 모든 땅에서 갈수있는 최대거리 탐색
            // 이부분은 class로 안하고 이차원배열로 값받는게 편할듯 너무 코드더럽
            int count=0;
            int maxcount=0;
            Land land=lands.poll();
            bfsland.offer(new Searchland(land.x,land.y,count));
            visited[land.x][land.y]=true;
            while(!bfsland.isEmpty()){  // 기본적인 bfs 탐색
                    Searchland sl=bfsland.poll();
                    for(int k=0;k<4;k++){
                        int x=sl.x+dx[k];
                        int y=sl.y+dy[k];
                        if(x>=0 && x<height && y>=0 && y<width && !visited[x][y] && map[x][y].equals("L")){
                            visited[x][y]=true;
                            bfsland.offer(new Searchland(x,y, sl.count+1));
                            if(maxcount<sl.count+1){
                                maxcount=sl.count+1;
                            }
                        }
                    }
            }
            maxarr[index++]=maxcount;
            for(int u=0;u<height;u++){ // 방문 초기화
                Arrays.fill(visited[u],false);
            }


        }

        Arrays.sort(maxarr);

        System.out.println(maxarr[maxarr.length-1]);



    }
}
