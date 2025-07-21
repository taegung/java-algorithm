import java.util.*;
class 지게차{

    static int dx [] ={-1,1,0,0};
    static int dy [] ={0,0,1,-1};
    static Character map[][];
    static boolean visited[][];
    static Character newmap[][];
    static class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public int solution(String[] storage, String[] requests) {

        int n=storage.length; //세로
        int m=storage[0].length();
        map =new Character[n][m];
        visited =new boolean[n][m];

        for(int i=0;i<n;i++){
            String str = storage[i];
            for(int j=0;j<m;j++){
                map[i][j]=str.charAt(j);
            }
        }

        for(int i=0;i<requests.length;i++){

            String str =requests[i];
            int len =str.length();
            char box= str.charAt(0);

            if(len==1){ // 지게차
                newmap =new Character[n][m];
                for(int a=0;a<n;a++){
                    newmap[a]=Arrays.copyOf(map[a],m);
                }

                for(int q=0;q<n;q++){
                    for(int w=0;w<m;w++){

                        if(q==0 || q==n-1 || w==0 ||
                                w==m-1 ) {
                            if (!visited[q][w] && map[q][w] == box) {
                                newmap[q][w] = '0';
                            }

                            if (map[q][w] == '0') {
                                if (!visited[q][w]) {
                                    bfs(q, w, n, m, box);
                                }
                            }
                        }
                    }
                }

                for(int t=0;t<n;t++){
                    map[t]=Arrays.copyOf(newmap[t],m);
                    Arrays.fill(visited[t],false);
                }


            }
            else{ //크레인
                for(int q=0;q<n;q++){
                    for(int w=0;w<m;w++){
                        if(map[q][w]==box){
                            map[q][w]='0';
                        }
                    }
                }
            }


        }
        int answer = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]!='0'){
                    answer++;
                }
            }
        }




        return answer;
    }

    static void bfs(int x,int y,int n,int m,char box){

        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(x,y));
        visited[x][y]=true;
        newmap[x][y]='0';
        while(!dq.isEmpty()){
            Point cur = dq.poll();
            for(int i=0;i<4;i++){
                int gox = cur.x+dx[i];
                int goy = cur.y+dy[i];
                if(gox<0 || gox >= n || goy<0 || goy>=m  || visited[gox][goy]){
                    continue;
                }

                if(map[gox][goy]=='0'){
                    dq.offer(new Point(gox,goy));
                    visited[gox][goy]=true;
                }else if(map[gox][goy]==box){
                    newmap[gox][goy]='0';
                }

            }
        }

    }
}