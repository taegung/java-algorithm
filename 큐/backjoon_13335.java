import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class backjoon_13335 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        String num [] = br.readLine().split(" ");
        int N = Integer.parseInt(num[0]); //트럭 개수
        int W = Integer.parseInt(num[1]); // 다리 길이
        int L=  Integer.parseInt(num[2]);  // 다리 무게

        String strtruck[] =br.readLine().split(" ");
        int truck [] =new int[N];

        for(int i=0;i<N;i++){
            truck[i]=Integer.parseInt(strtruck[i]);
        }

        int cnt=0;
        for(int i=0;i<W;i++) {
            q.add(1001);
        }

        int index=0;
        int sumtruck=0;
        int outtruck=1001;
        int truck1; //들어갈 트럭
        int clear=0;  // clear 1로 바꾸면 트럭 다들어감 나오기만하면됨
        while(!q.isEmpty()){
            outtruck=q.poll();
            if(outtruck==truck[N-1] && index==N){
                break;
            }else if(index==N){// clear 1로 바꾸면 트럭 다들어감 나오기만하면됨
                clear=1;
                break;
            }
            else{
                truck1=truck[index];
            }


            if(outtruck!=1001){//트럭이 나감
                sumtruck-=outtruck;
            }


            sumtruck+=truck1;
            if(sumtruck<=L){
                q.add(truck1);
                index++;
            }else{
                q.add(1001);
                sumtruck-=truck1;
            }
            cnt++;

        }

        while(!q.isEmpty()){
            q.poll();
            cnt++;
        }

        System.out.println(cnt+1);

    }
}
