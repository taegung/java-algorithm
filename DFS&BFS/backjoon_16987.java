import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon_16987 {
    static int max=0;
    static int N;
    static int egg[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        egg =new int[N][2]; // 0내구도 1무게
        boolean braeken[] =new boolean[N];
        for(int i=0;i<N;i++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            egg[i][0]=Integer.parseInt(st.nextToken());
            egg[i][1]=Integer.parseInt(st.nextToken());
        }


            dfs(0,0);

        System.out.println(max);
    }

    static void dfs(int start ,int breaked){

            if(start==N || breaked==N){
                if(max<breaked){
                    max=breaked;
                }
                return;
            }
            int eggin=egg[start][0];
            int eggw =egg[start][1];
            if(eggin>0) {
                for (int i = 0; i < N; i++) {
                    if (start == i) continue;
                    int neweggin = egg[i][0];
                    int neweggw = egg[i][1];
                    if(neweggin>0){//안깨진거 잡으면
                        egg[start][0]=eggin-neweggw;
                        egg[i][0]=neweggin-eggw;
                        if(egg[start][0]<=0&&egg[i][0]<=0){
                            dfs(start+1,breaked+2);
                        }else if(egg[start][0]<=0 || egg[i][0]<=0){
                            dfs(start+1,breaked+1);
                        }else{
                            dfs(start+1,breaked);
                        }
                        egg[start][0]=eggin;
                        egg[i][0]=neweggin;
                    }{
                        if(max<breaked){
                            max=breaked;
                        }
                    }
                }
            }else{
                dfs(start+1,breaked);
            }




    }


}
