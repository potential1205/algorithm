
import heapq
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        n,k,t,m = map(int,input().split())

        team = [dict()]
        for team_num in range(1,n+1):
            team.append(dict())
            team[team_num]["cnt"] = 0
            team[team_num]["last"] = 0
            for problem_num in range(1,k+1):
                team[team_num][problem_num] = 0

        for i in range(m):
            t_id, p_id, score = map(int,input().split())
            team[t_id]["cnt"] += 1
            team[t_id]["last"] = i

            if team[t_id][p_id] < score:
                team[t_id][p_id] = score
      
        result = []
        for t_id in range(1,n+1):
            cum = 0
            for j in range(1,k+1):
                cum += team[t_id][j]
            
            heapq.heappush(result,[-cum,team[t_id]["cnt"],team[t_id]["last"],t_id])

        for i in range(1,n+1):
            line = heapq.heappop(result)
            if line[3] == t:
                print(i)
                break

