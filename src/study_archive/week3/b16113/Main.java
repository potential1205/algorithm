package study_archive.week3.b16113;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;

    static List<Integer> answer = new ArrayList<>();

    static char[][] numbers = {
            {'#','#','#',  '#','.','#',  '#','.','#',  '#','.','#',  '#','#','#'}, // 0

            {'.','#','.',  '.','#','.',  '.','#','.',  '.','#','.',  '.','#','.'}, // 1

            {'#','#','#',  '.','.','#',  '#','#','#',  '#','.','.',  '#','#','#'}, // 2

            {'#','#','#',  '.','.','#',  '#','#','#',  '.','.','#',  '#','#','#'}, // 3

            {'#','.','#',  '#','.','#',  '#','#','#',  '.','.','#',  '.','.','#'}, // 4

            {'#','#','#',  '#','.','.',  '#','#','#',  '.','.','#',  '#','#','#'}, // 5

            {'#','#','#',  '#','.','.',  '#','#','#',  '#','.','#',  '#','#','#'}, // 6

            {'#','#','#',  '.','.','#',  '.','.','#',  '.','.','#',  '.','.','#'}, // 7

            {'#','#','#',  '#','.','#',  '#','#','#',  '#','.','#',  '#','#','#'}, // 8

            {'#','#','#',  '#','.','#',  '#','#','#',  '.','.','#',  '#','#','#'}, // 9
    };

    static char[][] ones = {
            {'.','#',  '.','#',  '.','#',  '.','#',  '.','#'}, // 1
            {'#','.',  '#','.',  '#','.',  '#','.',  '#','.'}, // 1
    };

    static void getNum(char[] temp){
        for(int i=0; i<10; i++){
            int cnt = 0;

            for(int j=0; j<15; j++){
                if(temp[j]==numbers[i][j]){
                    cnt++;
                }
            }

            if(cnt==15){
                answer.add(i);
                break;
            }
        }
    }

    static void getOne(char[] temp){
        int cnt = 0;

        for(int j=0; j<10; j++){
            if(temp[j]==numbers[1][j]){
                cnt++;
            }
        }

        if(cnt==10){
            answer.add(1);
        }

    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        String input = sc.next();
        if(N==5){
            System.out.print(1);
        } else{

            int size = N/5;

            int cnt = 0;
            for(int i=0; i<5; i++){
                if(input.charAt(i*size) == '#'  && input.charAt(i*size+1) == '.'){
                    cnt++;
                }
            }

            if(cnt==5){
                answer.add(1);
            }


            for(int i=0; i<size-2; i++){
                if((input.charAt(i)=='#' && input.charAt(i+1)=='#' &&  input.charAt(i+2)=='#') || (input.charAt(i)=='#' && input.charAt(i+1)=='.' &&  input.charAt(i+2)=='#') ){
                    char[] temp = new char[15];

                    for(int j=0; j<5; j++){
                        for(int k=0; k<3; k++){
                            temp[j*3+k] = input.charAt(k + j*size + i);
                        }
                    }

                    getNum(temp);

                } else if(input.charAt(i)=='.' && input.charAt(i+1)=='#' && input.charAt(i+2)=='.'){
                    char[] temp = new char[15];

                    for(int j=0; j<5; j++){
                        for(int k=0; k<3; k++){
                            temp[j*3+k] = input.charAt(k + j*size + i);
                        }
                    }

                    getOne(temp);
                }
            }

            cnt = 0;
            for(int i=0; i<5; i++){
                if(input.charAt((i+1)*size-2) == '.'  && input.charAt((i+1)*size-1) == '#'){
                    cnt++;
                }
            }

            if(cnt==5){
                answer.add(1);
            }


            for(Integer val : answer){
                System.out.print(val);
            }
        }



    }
}
