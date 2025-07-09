import java.util.*;
class 택배상자 {
    public int solution(int n, int w, int num) {

        int size = n/w+1;
        int arr[][] =new int[size][w];
        int x=0;
        int y=0;
        int dir =0; // 0이면 x축으로++  1이면 x축으로 --
        for(int i=1;i<n+1;i++){  //택배 쌓기
            if(x==-1 || x==w){
                if(dir==0){
                    y++;
                    x--;
                    arr[y][x]=i;
                    dir=1;
                    x--;
                }else{
                    y++;
                    x++;
                    arr[y][x]=i;
                    dir=0;
                    x++;
                }
            } else {
                arr[y][x]=i;
                if(dir==0){
                    x++;
                }else{
                    x--;
                }
            }

        }

        for(int i=0;i<size;i++){
            for(int a=0;a<w;a++){
                System.out.print(arr[i][a]+" ");
            }
            System.out.println();
        }

        int flag=0; // 1이면 나감
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0;i<w;i++){
            for(int q=0;q<size;q++){
                int number=arr[size-q-1][i];
                if(number==num){
                    dq.add(number);
                    flag=1;
                    break;
                }else if(number!=0){
                    dq.add(number);
                }

            }

            if(flag==1) break;
            dq.clear();

        }
        int count=0;
        while(!dq.isEmpty()){
            count++;
            int num1=dq.poll();
            if(num1==num){
                break;
            }
        }


        return count;
    }
}