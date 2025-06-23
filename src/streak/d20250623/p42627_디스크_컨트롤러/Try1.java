package streak.d20250623.p42627_디스크_컨트롤러;

import java.util.*;

class Try1 {

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

        Queue<Job> queue = new LinkedList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>();
        Job execJob = null;

        int t = 0;
        int finishJobCnt = 0;

        for (int i = 0; i < jobs.length; i++) {
            queue.offer(new Job(i, jobs[i][0], jobs[i][1]));
        }

        while (true) {

            // 조건에 맞는 애들 큐에 저장
            while (!queue.isEmpty() && queue.peek().requestTime <= t) {
                Job job = queue.poll();
                pq.offer(job);
            }

            // 실행중인 작업 확인
            if (execJob != null && execJob.endTime == t) {
                int timeGap = execJob.endTime - execJob.requestTime;

                System.out.println("정보 : " + execJob.startTime + " " + execJob.endTime);

                answer += timeGap;
                finishJobCnt++;
                execJob = null;

                if (finishJobCnt == jobs.length) {
                    break;
                }
            }

            // 실행중인 작업이 없다면 대기 큐에서 꺼내 등록
            if (execJob == null && !pq.isEmpty()) {
                execJob = pq.poll();
                execJob.startTime = t;
                execJob.endTime = t + execJob.jobTime;
            }

            t++;
        }

        return answer / finishJobCnt;
    }
}