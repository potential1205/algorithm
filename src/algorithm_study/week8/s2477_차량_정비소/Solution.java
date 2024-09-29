package algorithm_study.week8.s2477_차량_정비소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static int T,N,M,K,A,B,answer;
    static int[] processTimeA, processTimeB;
    static int[] availableTimeA, availableTimeB;
    static Customer[] customerForA, customerForB;

    static List<Customer> customerList, resultList;

    static PriorityQueue<Customer> queue;

    static class Customer implements Comparable<Customer>{
        int id, taskA, taskB, visitTime, endTimeA;

        Customer(int id, int visitTime){
            this.id = id;
            this.visitTime = visitTime;
        }

        @Override
        public int compareTo(Customer o) {
            int rank1 = this.endTimeA - o.endTimeA;
            if(rank1==0) return this.taskA - o.taskA;
            else return rank1;
        }
    }

    static int getAvailableDesk(int time, int[] availableTimeArr, Customer[] customerArr){
        for(int i=0; i<customerArr.length; i++){
            if(availableTimeArr[i] <= time && customerArr[i]==null) return i;
        }
        return -1;
    }


    static void endTaskA(int time){
        for(int i=0; i<N; i++){
            if(availableTimeA[i]<=time && customerForA[i] != null){
                Customer customer = customerForA[i];
                customer.endTimeA = time;
                queue.offer(customer);
                customerForA[i] = null;
            }
        }
    }

    static void endTaskB(int time){
        for(int i=0; i<M; i++){
            if(availableTimeB[i]==time && customerForB[i]!=null){
                resultList.add(customerForB[i]);
                customerForB[i] = null;
            }
        }
    }

    static void assignTaskA(int time){
        while(true){
            if(customerList.isEmpty() || customerList.get(0).visitTime>time) return;

            int index = getAvailableDesk(time, availableTimeA, customerForA);

            if(index==-1) return;

            Customer customer = customerList.remove(0);
            customer.taskA = index;
            availableTimeA[index] = time + processTimeA[index];
            customerForA[index] = customer;
        }
    }


    static void assignTaskB(int time){
        while(true){
            if(queue.isEmpty()) return;

            int index = getAvailableDesk(time, availableTimeB, customerForB);

            if(index==-1) return;

            Customer customer = queue.poll();
            customer.taskB = index;
            availableTimeB[index] = time + processTimeB[index];
            customerForB[index] = customer;
        }
    }

    static void solution(int tc){
        for(int t=0; t<=10000; t++){
            endTaskA(t);
            assignTaskA(t);
            endTaskB(t);
            assignTaskB(t);
        }

        answer = 0;
        for(Customer customer : resultList){
            if(customer.taskA == A-1 && customer.taskB == B-1){
                answer += customer.id;
            }
        }

        if(answer==0) answer = -1;
        System.out.println("#" + tc + " " + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(bf.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st  = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            processTimeA = new int[N];
            processTimeB = new int[M];

            availableTimeA = new int[N];
            availableTimeB = new int[M];

            customerForA = new Customer[N];
            customerForB = new Customer[M];

            customerList = new ArrayList<>();
            resultList = new ArrayList<>();

            queue = new PriorityQueue<>();

            st  = new StringTokenizer(bf.readLine());
            for(int i=0; i<N; i++){
                processTimeA[i] = Integer.parseInt(st.nextToken());
            }

            st  = new StringTokenizer(bf.readLine());
            for(int i=0; i<M; i++){
                processTimeB[i] = Integer.parseInt(st.nextToken());
            }

            st  = new StringTokenizer(bf.readLine());
            for(int i=0; i<K; i++){
                int visitTime = Integer.parseInt(st.nextToken());
                customerList.add(new Customer(customerList.size()+1, visitTime));
            }

            solution(t);
        }
    }
}
