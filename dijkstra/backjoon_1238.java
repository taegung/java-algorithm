import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class backjoon_1238 {

    static class Node implements Comparable<Node>{
        int x;
        int cnt;
        public Node(int x,int cnt){
            this.x=x;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node node){
            return this.cnt - node.cnt;
        }
    }

    static ArrayList<Node> [] graph;
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken()); //노드 수
        int M= Integer.parseInt(st.nextToken()); //간선 수
        int party= Integer.parseInt(st.nextToken());  //파티 위치

        graph =new ArrayList[N+1];
        for(int i=0;i<N+1; i++){
            graph[i]=new ArrayList<>();
        }

        for(int i =0 ;i<M;i++){ //버스 입력
            StringTokenizer df =new StringTokenizer(br.readLine());
            int a =Integer.parseInt(df.nextToken()); // 도시 출발
            int b =Integer.parseInt(df.nextToken()); // 도시 도착
            int c =Integer.parseInt(df.nextToken()); // 가중치
            graph[a].add(new Node(b,c));
        }

        int start[] = new int[N+1];  // 파티까지 가는 시간
        int end[] = new int [N+1];   // 파티에서 돌아오는 시간
        int sum[] = new int [N+1];
        for(int i=1;i<N+1;i++){
            if(i!=party){
                start[i]=di(i,party,N);
                end[i]=di(party,i,N);
                sum[i]=start[i]+end[i];
            }else{
                sum[i]=0;;
            }
        }

//        for(int arr:sum){
//            System.out.println(arr);
//        }


        int max = Arrays.stream(sum).max().getAsInt();
        System.out.println(max);
    }

    public static int di(int start,int end, int nodenum){

        int digst[] =new int [nodenum+1];
        int INF = Integer.MAX_VALUE;
        boolean visited[] =new boolean[nodenum+1];
        Arrays.fill(digst,INF);


        PriorityQueue<Node> pq =new PriorityQueue<>();

        pq.add(new Node(start,0));
        digst[start]=0;
        while(!pq.isEmpty()){
            Node cur= pq.poll();

            if(visited[cur.x]){
                continue;
            }
            visited[cur.x]=true;
            for(Node next :graph[cur.x]){
                if(digst[next.x]>digst[cur.x]+ next.cnt){
                    digst[next.x]=digst[cur.x]+ next.cnt;
                    pq.offer(new Node(next.x,digst[next.x]));
                }
            }

        }

        return digst[end];
    }

}
