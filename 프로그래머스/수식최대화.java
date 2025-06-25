import java.util.*;
public class 수식최대화 {
    class Solution {
        public long solution(String expression) {

            ArrayList<Long> price = new ArrayList<>();
            ArrayList<Character> op = new ArrayList<>();
            String str="";
            for(int i=0;i<expression.length();i++){

                char ex=expression.charAt(i);
                if(ex=='+'||ex=='-'||ex=='*'){
                    price.add(Long.parseLong(str));
                    op.add(ex);
                    str="";
                }else{
                    str=str+String.valueOf(ex);
                }

            }
            price.add(Long.parseLong(str));


            for(Long i : price){
                System.out.println(i);
            }
            char arr[][] =new char[6][3];

            arr[0][0]='-';
            arr[0][1]='*';
            arr[0][2]='+';

            arr[1][0]='-';
            arr[1][1]='+';
            arr[1][2]='*';

            arr[2][0]='+';
            arr[2][1]='*';
            arr[2][2]='-';

            arr[3][0]='+';
            arr[3][1]='-';
            arr[3][2]='*';

            arr[4][0]='*';
            arr[4][1]='+';
            arr[4][2]='-';

            arr[5][0]='*';
            arr[5][1]='-';
            arr[5][2]='+';

            Long aw[] =new Long[6];
            for(int tc=0;tc<6;tc++){
                int arrindex=0;
                while(price.size()!=1 && arrindex < arr.length){
                    char op1 =arr[tc][arrindex];

                    if(op.contains(op1)){
                        int index=op.indexOf(op1);
                        Long num1 =price.get(index);
                        Long num2 =price.get(index+1);
                        if(op1=='+'){
                            price.set(index, num1+num2);
                        }
                        else if(op1=='-'){
                            price.set(index, num1-num2);
                        }
                        else{  //op=='*'
                            price.set(index, num1*num2);
                        }

                        price.remove(index+1);
                        op.remove(index);


                    }else{
                        arrindex++;
                    }



                }

                long answer = price.get(0);
                answer =Math.abs(answer);
                aw[tc]=answer;
                str="";
                op.clear();
                price.clear();
                for(int i=0;i<expression.length();i++){

                    char ex=expression.charAt(i);
                    if(ex=='+'||ex=='-'||ex=='*'){
                        price.add(Long.parseLong(str));
                        op.add(ex);
                        str="";
                    }else{
                        str=str+String.valueOf(ex);
                    }

                }
                price.add(Long.parseLong(str));



            }



            Arrays.sort(aw);
            return aw[5];
        }
    }
}
