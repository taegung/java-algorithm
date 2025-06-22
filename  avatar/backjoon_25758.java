import java.io.*;
import java.util.*;

public class backjoon_25758 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 유전자 입력 및 중복 2개까지만 허용
        for (int i = 0; i < N; i++) {
            String str = st.nextToken();
            if (map.get(str) != null && map.get(str) >= 2) continue;
            map.put(str, map.getOrDefault(str, 0) + 1);
            set.add(str);
        }

        TreeSet<Character> geneSet = new TreeSet<>();

        // 자기 자신과 조합 (동일 유전자가 2개 이상일 때만)
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value == 2) {
                char ch = makeGene(key, key);
                geneSet.add(ch);
            }
        }

        // 서로 다른 유전자 조합
        for (String a : set) {
            for (String b : set) {
                if (a.equals(b)) continue;
                char ch = makeGene(a, b);
                geneSet.add(ch);
            }
        }

        // 출력
        sb.append(geneSet.size()).append('\n');
        for (char ch : geneSet) {
            sb.append(ch).append(' ');
        }

        System.out.println(sb);
    }

    private static char makeGene(String a, String b) {
        char ch1 = a.charAt(0);
        char ch2 = b.charAt(1);
        return (ch1 > ch2) ? ch1 : ch2;
    }
}
