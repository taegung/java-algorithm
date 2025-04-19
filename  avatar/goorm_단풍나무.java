import java.io.*;
import java.util.*;

class goorm_단풍나무{

    static int[] directx = {0, 0, -1, 1}; // 우좌상하
    static int[] directy = {1, -1, 0, 0};
    static int zeronum = 0; // 단풍물든 지역 개수
    static int[][] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int cnt = 0;
        arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(str.nextToken());
                if (arr[i][j] == 0) {
                    zeronum++;
                }
            }
        }

        while (zeronum != size * size) {
            cnt++;

            // 오늘 아침 기준 상태 저장
            int[][] todayarr = new int[size][];
            for (int i = 0; i < size; i++) {
                todayarr[i] = arr[i].clone(); //복사
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (todayarr[i][j] != 0) {
                        search(todayarr, i, j, size); // 오늘 아침 기준 판단
                    }
                }
            }
        }

        System.out.println(cnt);
    }

    static void search(int[][] todayarr, int x, int y, int size) {


        for (int i = 0; i < 4; i++) {
            int startx = x + directx[i];
            int starty = y + directy[i];
            if (startx >= 0 && starty >= 0 && startx < size && starty < size) {
                if (todayarr[startx][starty] == 0) {
                    arr[x][y]=arr[x][y]-1;
                    if(arr[x][y]==0){
                        zeronum++;
                        break;
                    }
                }
            }
        }


    }
}
