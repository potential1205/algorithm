package daily.y2025.m06.d23.p42627_디스크_컨트롤러;

import java.util.*;

class Solution {

    static class Job implements Comparable<Job> {
        int id;
        int requestTime;
        int jobTime;
        int startTime;
        int endTime;

        Job(int id, int requestTime, int jobTime) {
            this.id = id;
            this.requestTime = requestTime;
            this.jobTime = jobTime;
        }

        @Override
        public int compareTo(Job o) {
            if (this.jobTime != o.jobTime) {
                return this.jobTime - o.jobTime;
            } else if (this.requestTime != o.requestTime) {
                return this.requestTime - o.requestTime;
            } else {
                return this.id - o.id;
            }
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<Job> pq = new PriorityQueue<>();

        int t = 0;
        int startJobCnt = 0;
        int finishJobCnt = 0;

        while (finishJobCnt < jobs.length) {

            // 조건에 맞는 애들 큐에 저장
            while (startJobCnt < jobs.length && jobs[startJobCnt][0] <= t) {
                Job job = new Job(startJobCnt, jobs[startJobCnt][0], jobs[startJobCnt][1]);
                pq.offer(job);
                startJobCnt++;
            }

            if (pq.isEmpty()) {
                t = (jobs[startJobCnt][0]);
            } else {
                Job job = pq.poll();
                t += job.jobTime;
                answer += (t - job.requestTime);
                finishJobCnt++;
            }
        }

        return answer / jobs.length;
    }
}