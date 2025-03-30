import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class backjoon_12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int S = Integer.parseInt(s[0]);
        int P = Integer.parseInt(s[1]);
        String str = br.readLine();
        String[] DNACOUNT = br.readLine().split(" ");

        int DNA[] = new int[4]; // A,C,G,T순

        for (int i = 0; i < 4; i++) {
            DNA[i] = Integer.parseInt(DNACOUNT[i]);
        }

        int start = 0;
        int end = start + P;   //0+8
        int result = 0;
        int first = 0;
        int a = 0, c = 0, g = 0, t = 0;

        String DNAstr = str.substring(start, end);

        for(int i=0;i<DNAstr.length();i++) {
            switch (DNAstr.charAt(i)) {
                case 'A': a++; break;
                case 'C': c++; break;
                case 'G': g++; break;
                case 'T': t++; break;
                default: break;
            }
        }
        if(DNA[0]<=a && DNA[1]<=c && DNA[2]<=g && DNA[3]<=t) {
            result++;
        }

        int index=0;
        while (index+P<str.length()) {
            char mlus =str.charAt(index);
            switch (mlus) {
                case 'A': a--; break;
                case 'C': c--; break;
                case 'G': g--; break;
                case 'T': t--; break;
                default: break;
            }

            char plus = str.charAt(index+P);
            switch (plus) {
                case 'A': a++; break;
                case 'C': c++; break;
                case 'G': g++; break;
                case 'T': t++; break;
                default: break;
            }
            if(DNA[0]<=a && DNA[1]<=c && DNA[2]<=g && DNA[3]<=t) {
                result++;
            }
            index++;
        }


        System.out.println(result);


    }
}









