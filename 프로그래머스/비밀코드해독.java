import java.util.*;
class Solution {

    static int arr[];
    static int num[];
    static List<List<Integer>> result1 =new ArrayList<>();
    public int solution(int n, int[][] q, int[] ans) {

        arr =new int[n];
        num =new int[5];

        for(int i=0;i<n;i++){
            arr[i]=i+1;
        }

        combine(0,0,5);

        int answer = 0;
        System.out.print(q.length);
        for(int i=0;i<result1.size();i++){
            int flag=1; // flag가 1이면 다 맞는거
            for(int w=0;w<q.length;w++){

                int a1=result1.get(i).get(0);
                int a2=result1.get(i).get(1);
                int a3=result1.get(i).get(2);
                int a4=result1.get(i).get(3);
                int a5=result1.get(i).get(4);
                int ans1=0;
                for(int e =0;e<5;e++){
                    if(a1==q[w][e] ||a2==q[w][e]||
                            a3==q[w][e]||a4==q[w][e]||a5==q[w][e]){
                        ans1++;
                    }
                }
                if(ans1!=ans[w]) {flag=0;break;}
            }

            if(flag==1){
                answer++;
            }

        }

        return answer;
    }

    public void combine(int cnt,int idx,int n){ //완전탐색을 위한 조합

        if(cnt==n){
            List<Integer> temp = new ArrayList<>();
            for(int i=0;i<n;i++){
                temp.add(num[i]);
            }
            result1.add(temp);
            return;
        }

        for(int i=idx;i<arr.length;i++){
            num[cnt]=arr[i];
            combine(cnt+1,i+1,n);
        }

    }


}