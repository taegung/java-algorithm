import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class backjoon15666 {
    static int N;
    static int M;
    static int arr[];
    static int result[];
    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        HashSet<Integer> h1 =new HashSet<>();
        arr=new int[N];
        result=new int[M];
        StringTokenizer st1 =new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            h1.add(Integer.parseInt(st1.nextToken()));
        }
        N=h1.size();
        arr=new int[N];
        int i=0;
        for ( int element : h1) {
            arr[i++]=element;
        }
        Arrays.sort(arr);
//        for(int arr :arr){
//            System.out.println(arr);
//        }

         dfs(0,0,M);

    }

    static void dfs(int cnt, int aidx,int r){

        if(cnt==r){
            //System.out.println("-- "+cnt+" "+aidx+"----");
            for(int i=0;i<M;i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=aidx;i<N;i++){
            result[cnt]=arr[i];
            dfs(cnt+1,i,r);
        }
    }

}
