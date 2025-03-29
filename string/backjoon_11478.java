import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class backjoon_11478 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s =br.readLine();
        HashMap<String,Integer> map =new HashMap<>();
        int size =s.length();

        for(int i=1;i<size+1;i++){

            int start=0;
            int end=start+i;
            while(end<=size) {
                    String str=s.substring(start,end);
                    map.put(str,0);
                    start++;
                    end=start+i;
            }
        }


        System.out.println(map.size());

    }
}
