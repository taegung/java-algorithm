import java.util.*;


class 부대복귀 {
    static ArrayList<Node> [] graph;
    static final int INF = Integer.MAX_VALUE;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {


        graph= new ArrayList[n+1];
        for(int i=0;i<n+1;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<roads.length;i++){
            int start=roads[i][0];
            int end=roads[i][1];
            //양방향
            graph[start].add(new Node(end,1));
            graph[end].add(new Node(start,1));
        }

        int dp[] =new int[n+1];
        Arrays.fill(dp,INF);
        PriorityQueue<Node> pq =new PriorityQueue<>();
        dp[destination]=0;
        pq.offer(new Node(destination,0));

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(dp[cur.x]<cur.cnt) continue;

            for( Node next : graph[cur.x]){
                if(dp[next.x]> next.cnt+dp[cur.x]){
                    dp[next.x] =next.cnt+dp[cur.x];
                    pq.offer(new Node(next.x,dp[next.x]));
                }

            }

        }

        int[] answer = new int[sources.length];

        for(int i=0;i<sources.length;i++){

            if(dp[sources[i]]==INF){
                answer[i]= -1;
            }else{
                answer[i]=dp[sources[i]];
            }
        }




        return answer;
    }



    static class Node implements Comparable<Node>{
        int x;
        int cnt;
        public Node(int x,int cnt){
            this.x=x;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node node){
            return this.cnt-node.cnt;
        }

    }

}