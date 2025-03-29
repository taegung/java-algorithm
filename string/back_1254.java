import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class back_1254 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str =br.readLine();



        boolean a =plan(str);

        if(a==true){
            System.out.println(str.length());
        }else{
            String str2="";
            int maxlen=0;
            for( int i=str.length()-1;i>=0;i--){
                str2+=str.charAt(i);
                if(plan(str2)){
                    maxlen=str2.length();
                }

            }
            int pluslen=str.length()-maxlen;
            System.out.println(str.length()+pluslen);


        }


    }


    static boolean plan(String str){ //판단
        int len = str.length();
        int flag = len%2;  // 0이면 짝수
        if(flag==0){
            String str1=str.substring(0,len/2);
            String str2=str.substring(len/2);
            String str3 = "";
            for( int i=str2.length()-1;i>=0;i--){
                str3+=str2.charAt(i);
            }

            if(str3.equals(str1)){
                return true;
            }else{
                return false;
            }

        }else{ //홀수
            String str1=str.substring(0,len/2);
            String str2=str.substring(len/2+1);
            String str3 = "";
            for( int i=str2.length()-1;i>=0;i--){
                str3+=str2.charAt(i);
            }

            if(str3.equals(str1)){
                return true;
            }else{
                return false;
            }

        }
    }




}
