import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class backjoon_2002 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =Integer.parseInt(br.readLine());

        HashMap<String,Integer> incar =new HashMap<>();
        HashMap<String,Integer> outcar =new HashMap<>();
        String car[] =new String[N];
        // a b c d

        int cnt=0;

        for(int i=0;i<N;i++){
            String carstr=br.readLine();
            incar.put(carstr,i+1);
            car[i]=carstr;
        }
        for(int i=0;i<N;i++){
            outcar.put(br.readLine(),i+1);
        }


        for(int i=0;i<N;i++){
            if(incar.get(car[i])>outcar.get(car[i])){
                cnt++;
            }else if(outcar.get(car[i])==incar.get(car[i])){
                //출발등수랑 도착등수가 같아도 추월가능함
                //ex a  b c
                //   c  b a   이러면 b는 등수가같아도 a를 추월함
                int first =incar.get(car[i]);// 등수나오자나
                int flag=0;
                for(int j=1;j<first;j++){
                    //키값찾기 j등수
                    for(String key:incar.keySet()){
                        if(incar.get(key)==j && (outcar.get(key)>incar.get(key))
                                && (outcar.get(key)>outcar.get(car[i])) )
                        {
                              flag=1;
                              cnt++;
                              break;
                        }
                    }
                    if(flag==1){
                        break;
                    }
                }
            }else{  //순위가 낮은데  추월할수가 있나??
                   //bcd   , cba
                int first =incar.get(car[i]);  //b
                int last =outcar.get(car[i]);   //b
                int flag1=0;
                for(int j=1;j<first;j++){
                    for(String key:incar.keySet()){
                        if(incar.get(key)==j && outcar.get(key)>last){
                            flag1=1;
                            cnt++;
                            break;
                        }
                    }
                    if(flag1==1){
                        break;
                    }
                }
            }

        }

        System.out.println(cnt);


    }
}
