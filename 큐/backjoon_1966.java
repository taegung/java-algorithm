import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class backjoon_1966 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        for(int i=0;i<size;i++){//큰 반복문

            String num [] = br.readLine().split(" ");
            int N=Integer.parseInt(num[0]);
            int M=Integer.parseInt(num[1]);

            Queue<Integer> q = new LinkedList<>();
            String queue [] =br.readLine().split(" ");
            int Maxarr[] =new int[N];
            int Maxindex=N-1;
            int cnt=0;
            int Mnum=Integer.parseInt(queue[M]); // 알고싶은 문서 중요도
            for(int j=0;j<N;j++){
                if(j==M){ //우리가 알고싶은  문서 인덱스
                    q.add(10); //10으로 치환
                }else{
                    q.add(Integer.parseInt(queue[j])); //큐에 입력
                }

                Maxarr[j]=Integer.parseInt(queue[j]); //최대값알기위한 배열
            }

            Arrays.sort(Maxarr);
            int Max=Maxarr[Maxindex];//큐에 들어가 있는 문서의 중요도 최대값

            while(!q.isEmpty()){
                int qnum=q.poll(); //큐 첫번째 값

                if(qnum!=10){ //
                    if(qnum<Max){ //중요도가 낮음
                        q.add(qnum);
                    }else{
                        cnt++;
                        Maxindex=Maxindex-1;
                        Max=Maxarr[Maxindex];
                    }
                }else{  //알고싶은 문서
                    if(Mnum==Max){ //출력가능
                        cnt++;
                        break;
                    }else{
                        q.add(qnum);
                    }
                }
            }


            System.out.println(cnt);
        }



    }
}
