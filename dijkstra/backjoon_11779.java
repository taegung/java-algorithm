import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class backjoon_11779 {

    static class Node implements Comparable<Node> {
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

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

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

        dijkstra(start,end,N);

    }

    public static void dijkstra(int start, int end, int nodenum){

        int digst[] =new int [nodenum+1];
        boolean visited[] =new boolean[nodenum+1];
        int beforenode [] =new int[nodenum+1]; //  현재 노드가 이전에 누구를 통해 왔는지 저장
        int INF = Integer.MAX_VALUE;

        Arrays.fill(digst,INF);
        Arrays.fill(beforenode,INF);

        PriorityQueue<Node> pq =new PriorityQueue<>();

        pq.add(new Node(start,0));
        beforenode[start]=start;
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
                    beforenode[next.x]=cur.x;

                }
            }

        }



        int nodestart=end;
        int count=1;

        int arr[] =new int[nodenum+1]; // 노트 출력하면 역순 출력되서  배열 담을곳
        int arrindex =0;

        while(nodestart!=start){  //역순으로 추적해서  출력
            arr[arrindex++]=nodestart;
            //System.out.print(nodestart+" ");
            nodestart=beforenode[nodestart];
            count++;
        }
        System.out.println(digst[end]);// 최소 비용 출력 출력

        System.out.println(count); //방문수

        System.out.print(nodestart+" ");   //방문순서 출력
        for(int i=arrindex-1;i>=0;i--){
            System.out.print(arr[i]+" ");
        }





    }

}
