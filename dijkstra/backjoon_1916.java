import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_1916 {

    static class Node implements Comparable<Node> {
        int x; //연결할 노드
        int cnt;  // 가중치
        public  Node(int x ,int cnt){
            this.x =x;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.cnt - node.cnt;
        }

    }


    static   ArrayList<Node> []graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph =new ArrayList[N+1];

        for(int i=0;i<N+1;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i =0 ;i<M;i++){ //버스 입력
            StringTokenizer df =new StringTokenizer(br.readLine());
            int a =Integer.parseInt(df.nextToken()); // 버스 출발도시
            int b =Integer.parseInt(df.nextToken()); // 버스  도착도시
            int c =Integer.parseInt(df.nextToken()); // 가중치
            graph[a].add(new Node(b,c));
        }

        StringTokenizer df =new StringTokenizer(br.readLine());

        int start =Integer.parseInt(df.nextToken());
        int end =Integer.parseInt(df.nextToken());



       int answer= dikstra(start,end,N);
        System.out.println(answer);

    }

    public static int dikstra(int start,int end,int nodenum){
        int dist[] =new int[nodenum+1];
        boolean[] visited=new boolean[nodenum+1];
        int INF=Integer.MAX_VALUE;

        for(int i=1;i<nodenum+1;i++){
            dist[i]=INF;
        }

        PriorityQueue<Node> q =new PriorityQueue<>();

        q.offer(new Node(start,0));

        dist[start]=0;

        while(!q.isEmpty()){

            Node cur = q.poll(); //노드 꺼내기
            if(visited[cur.x]){
                continue;
            }
            visited[cur.x]=true;
            for(Node next : graph[cur.x]){
                if(dist[next.x]>dist[cur.x]+next.cnt){
                    dist[next.x]=dist[cur.x]+next.cnt;
                   q.offer(new Node(next.x,dist[next.x]));
                }
            }

        }

        return dist[end];


    }


}