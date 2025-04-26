import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_2178 {

    static int [] dx={0,0,-1,1};
    static int [] dy={1,-1,0,0};
    static int [][]arr;
    public class Node{
        int x;
        int y;

        public Node(int x,int y){
            this.x=x;
            this.y=y;
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tk =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tk.nextToken());
        int M = Integer.parseInt(tk.nextToken());

         arr =new int[N][M];

        for(int i=0;i<N;i++){
            StringTokenizer tk1 =new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j]=Integer.parseInt(tk.nextToken());
            }
        }

        bfs(N,M);
    }

    public static int bfs(int N,int M){



        return 0;
    }


}
