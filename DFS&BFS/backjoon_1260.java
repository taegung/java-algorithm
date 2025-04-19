import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_1260 {

     static ArrayList<Integer>[] graph;
     static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str =new StringTokenizer(br.readLine());

        int N= Integer.parseInt(str.nextToken());  // 노드 수
        int M= Integer.parseInt(str.nextToken());
        int V= Integer.parseInt(str.nextToken());   //시작점

        graph=new ArrayList[N+1];
        visited=new boolean[N+1];

        for(int i=0;i<N+1;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            StringTokenizer node =new StringTokenizer(br.readLine());
            int first=Integer.parseInt(node.nextToken());
            int second=Integer.parseInt(node.nextToken());

            graph[first].add(second);
            graph[second].add(first); //양방향
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]); // 오름차순 정렬
        }
        dfs(V);

        for(int i=0;i<N+1;i++){
            visited[i]=false;
        }

        System.out.println();
        bfs(V);
    }

    static void dfs(int v){

        visited[v]=true;
        System.out.print(v+" ");
        for(int next : graph[v]){
            if(!visited[next]){
                dfs(next);
            }
        }
    }


    static void bfs(int v){
        Deque<Integer> queue =new LinkedList<>();
        queue.offer(v);
        visited[v]=true;
        System.out.print(v+" ");
        while(!queue.isEmpty()){
            int cur=queue.poll();;
            for(int next: graph[cur]){
                if(!visited[next]){
                    System.out.print(next+" ");
                    visited[next]=true;
                    queue.offer(next);
                }
            }
        }
    }


}
