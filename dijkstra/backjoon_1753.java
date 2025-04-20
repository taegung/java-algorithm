import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_1753 {

    static class Node implements Comparable<Node> {
        int x;
        int cnt;

        public Node(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.cnt - node.cnt;
        }
    }



    static ArrayList<Node>[] graph;
    static int [] dist;
    public static void main(String[] args) throws IOException {


        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer df=new StringTokenizer(br.readLine());

        int V = Integer.parseInt(df.nextToken()); //정점 수
        int E = Integer.parseInt(df.nextToken()); // 간선의 수

        int StartNode = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1]; //0노드 없어서 +1해야함
        for(int i=0;i<=V;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){  //간선 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end,weight));
        }

        for(int i=1;i<=V;i++){
            Collections.sort(graph[i],(a,b)->a.cnt - b.cnt); //가중치 낮은순으로 정렬
        }


        dijkstra(V,StartNode);



    }

    public static void dijkstra(int nodenum,int start){

        boolean[] visited=new boolean[nodenum+1];
        int []dist =new int[nodenum+1];  //각각노듸의 최소거리 담을 배열
        int INF=Integer.MAX_VALUE;

        Arrays.fill(dist,INF);

        dist[start]=0;// 시작값
        PriorityQueue<Node> q =new PriorityQueue<>();
        q.offer(new Node(start,0));

        while(!q.isEmpty()){

            Node cur=q.poll();
            if(visited[cur.x]){
                continue;
            }
            visited[cur.x]=true;

            for(Node next:graph[cur.x]){

                if(dist[next.x]>dist[cur.x]+next.cnt) {
                    dist[next.x] = dist[cur.x] + next.cnt;
                    q.offer(new Node(next.x,dist[cur.x] + next.cnt));
                }
            }
        }


        for(int i =1;i<=nodenum;i++) {
            if(dist[i]!=INF){
                System.out.print(dist[i]+" ");
            }else{
                System.out.println("INF ");
            }
        }


    }


}
