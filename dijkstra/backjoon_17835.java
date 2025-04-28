import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_17835 {

    static class Node implements Comparable<Node> {
        int x;
        long cnt;

        public Node(int x, long cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cnt, node.cnt);  // long 비교
        }
    }

    static ArrayList<Node>[] graph;
    static int[] dq;   // 면접장 위치 담을 배열
    static long[] arr;  // 각 도시마다 면접장 거리 담을 배열
    static final long INF = Long.MAX_VALUE;  // INF를 long으로 변경

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 도시의 수
        int M = Integer.parseInt(st.nextToken());  // 도로 개수
        int K = Integer.parseInt(st.nextToken());  // 면접장의 수

        graph = new ArrayList[N + 1];
        dq = new int[K];
        arr = new long[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) { // 면접 입력
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken()); // 출발 도시
            int b = Integer.parseInt(st1.nextToken()); // 도착 도시
            int c = Integer.parseInt(st1.nextToken()); // 가중치
            graph[b].add(new Node(a, c));  // 역으로 면접장에서 나머지 도시 찾기 그래야 시간초과 안남 도시들이 많기 때문에 시간초과
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Arrays.fill(arr, INF);  // 초기화

        for (int i = 0; i < K; i++) {  // 면접장 위치 넣기
            int num = Integer.parseInt(st2.nextToken());
            dq[i] = num;
            arr[num] = 0L;  // 면접장이 자기 도시이면 거리 0
        }

        di();  // 다익스트라 실행

//        for(int i=0;i<K;i++){  // 면접장에서 도시 역추적
//            int start =dq[i];
//            di(start,N,K);
////            for(int j=0;j<K;j++){
////                int end =dq[j];
////                if(arr[start]!=0) {
////                    int min = di(start, end, N,K);
////                    if (arr[start] > min) {  //다른 면접장의 거리가 더짧으면 바꿔줌
////                        arr[start] = min;
////                    }
////                }
////            }
//        }

        long max = -1; // 최대 거리  // 0이면 모든도시가 면접장인경우 예외처리 못함
        int idx = 0;
        for (int i = 1; i <= N; i++) {
            if (arr[i] != INF && arr[i] > max) {
                max = arr[i];
                idx = i;
            }
        }
        System.out.println(idx);
        System.out.println(max);
    }

    public static void di() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 모든 면접장 넣기
        for (int i = 0; i < dq.length; i++) {
            pq.add(new Node(dq[i], 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (arr[cur.x] < cur.cnt) continue;   //원래는 visted으로 했는데 이건 동시에 면접장이있기떄문에 가중치 비교로

            for (Node next : graph[cur.x]) {
                if (arr[next.x] > arr[cur.x] + next.cnt) {
                    arr[next.x] = arr[cur.x] + next.cnt;
                    pq.offer(new Node(next.x, arr[next.x]));
                }
            }
        }
    }
}
