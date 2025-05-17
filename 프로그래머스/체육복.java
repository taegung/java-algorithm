import java.util.*;
class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int lostsize = lost.length;
        answer=n-lostsize;  //도난 안된사람
        HashSet<Integer> reseveman =new HashSet<Integer>();
        for(int i=0;i<reserve.length;i++){
            reseveman.add(reserve[i]);
        }

        Arrays.sort(lost);

        for(int i=0;i<lostsize;i++){
            int lost1 =lost[i];
            if(reseveman.contains(lost1)){
                reseveman.remove(lost1);
                answer++;
                lost[i]=31;
            }
        }


        for(int i=0;i<lostsize;i++){
            int lostman =lost[i];

            if(lostman!=31&&lostman-1>0 && reseveman.contains(lostman-1))            {
                reseveman.remove(lostman-1);
                answer++;
            }else if(lostman!=31&&lostman+1<n+1
                    &&reseveman.contains(lostman+1)){
                reseveman.remove(lostman+1);
                answer++;
            }


        }


        return answer;
    }
}