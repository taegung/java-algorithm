import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class backjoon_2606 {

    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static HashSet<Integer> h1=new HashSet<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num =Integer.parseInt(br.readLine()); // 노드 개수
        int linenum =Integer.parseInt(br.readLine());

        graph=new ArrayList[linenum+1];
        visited= new boolean[linenum+1];

        for(int i=0;i<linenum+1;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<linenum;i++){
            String line[]=br.readLine().split(" ");
            graph[Integer.parseInt(line[0])].add(Integer.parseInt(line[1])); // 그래프 연결  나중에 토크나이저
        }


        dfs(1);

        int cnt=h1.size()-1; //1번노드 뺴고

        System.out.println(cnt);

    }

    public static void dfs(int node){
        visited[node]=true;
        h1.add(node);
        System.out.println(node);
       for(int next :graph[node]){
           if(!visited[next]){
               dfs(next);
           }
       }

    }

}
