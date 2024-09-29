package algorithm_study.week5.s2382;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class YDSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for(int t=1; t<=tc; t++){
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); //한 변에 있는 셀의 개수
            int M = Integer.parseInt(st.nextToken()); //격리시간. 이 M 시간 후 남아있는 미생물 수의 총 합을 구해야 한다.
            int K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수

            int[][] micro = new int[K][4];
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1}; //순서대로 상하좌우
            for(int k = 0; k<K; k++){
                st = new StringTokenizer(bf.readLine());
                micro[k][0] = Integer.parseInt(st.nextToken()); //세로위치
                micro[k][1] = Integer.parseInt(st.nextToken()); //가로위치
                micro[k][2] = Integer.parseInt(st.nextToken()); //미생물 수
                micro[k][3] = Integer.parseInt(st.nextToken()) - 1; //이동방향
                // 상:1, 하:2, 좌:3, 우:4
            }

            for(int m=0; m<M; m++){
                //M시간 동안 이동
                //1. 이동 후 위치 및 미생물 수, 방향을 배열(micro)에 저장. { {0,0,1} }
                for(int k = 0; k<K; k++){
                    micro[k][0] = micro[k][0] + dx[micro[k][3]];
                    micro[k][1] = micro[k][1] + dy[micro[k][3]];

                    if(micro[k][0]==0 || micro[k][0]==(N-1) || micro[k][1]==0 || micro[k][1]==(N-1)){
                        //약품이 칠해진 셀에 도착하게 된다면 이동방향 반대로 + 미생물 수 반절
                        micro[k][2] = micro[k][2]/2;

                        if(micro[k][3]==1 || micro[k][3]==3){
                            micro[k][3] -= 1;
                        }else {
                            micro[k][3] += 1;
                        }
                    }
                }

                //2. 모든 미생물 이동 후 배열의 값들을 적절히 더하여 미생물 배열을 업데이트
                for(int k = 0; k<K; k++){
                    if(micro[k][2] == 0){
                        continue;
                    }

                    int x = micro[k][0];
                    int y = micro[k][1];
                    ArrayList<Integer> tmp_n = new ArrayList<>(); //미생물 수 임시 저장
                    ArrayList<Integer> tmp_di = new ArrayList<>(); //이동방향 임시 저장

                    for(int i=k+1; i<K; i++){
                        if(x == micro[i][0] && y == micro[i][1]){
                            //x, y 위치가 같다면 방향 및 미생물 수 업데이트
                            tmp_n.add(micro[i][2]);
                            tmp_di.add(micro[i][3]);

                            micro[i] = new int[]{0, 0, 0, 0};
                        }
                    }

                    if(!tmp_n.isEmpty()){
                        int idx = tmp_n.indexOf(Collections.max(tmp_n)); //최대값 인덱스 가져오기
                        for(int i: tmp_n){
                            micro[k][2] += i;
                        }
                        micro[k][3] = tmp_di.get(idx);
                    }
                }
            }

            int ans = 0;
            for(int k = 0; k<K; k++){
                ans += micro[k][2];
            }
            System.out.println("#" + t + " " + ans);
        }
    }
}
