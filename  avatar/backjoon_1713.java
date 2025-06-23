import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_1713 {

    static class Node implements Comparable<Node> {
        int person;
        int cnt;
        int clock;

        public Node(int person, int cnt, int clock) {
            this.person = person;
            this.cnt = cnt;
            this.clock = clock;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cnt != other.cnt) {
                return Integer.compare(this.cnt, other.cnt); // 투표 수 오름차순
            }
            return Integer.compare(this.clock, other.clock); // 오래된 순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frameSize = Integer.parseInt(br.readLine());
        int voteCount = Integer.parseInt(br.readLine());
        int[] votes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Node> photoMap = new HashMap<>();
        PriorityQueue<Node> frame = new PriorityQueue<>();

        for (int time = 0; time < voteCount; time++) {
            int student = votes[time];

            if (photoMap.containsKey(student)) {
                // 이미 있는 경우 -> 우선순위큐 재정렬 위해 객체 갱신
                Node oldNode = photoMap.get(student);
                frame.remove(oldNode);  // 우선순위큐 갱신을 위해 remove 후 재삽입
                oldNode.cnt++;
                frame.add(oldNode);
            } else {
                if (frame.size() == frameSize) {
                    Node removed = frame.poll();
                    photoMap.remove(removed.person);
                }
                Node newNode = new Node(student, 1, time);
                frame.add(newNode);
                photoMap.put(student, newNode);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Node n : frame) {
            result.add(n.person);
        }
        Collections.sort(result);
        for (int person : result) {
            System.out.print(person + " ");
        }
    }
}
