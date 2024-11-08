import sys
input = sys.stdin.readline
import heapq

if __name__ == "__main__":
    n = int(input())
    lst = []
    for i in range(n):
        s,t = map(int,input().split())
        heapq.heappush(lst,[s,t])

    room = []
    for i in range(1,n):
        heapq.heappush(room,i)

    answer = 0
    ing = []

    while lst:
        s,t = heapq.heappop(lst)

        while ing and s >= ing[0][0]:
            end,num = heapq.heappop(ing)
            heapq.heappush(room,num)

        room_num = heapq.heappop(room)
        heapq.heappush(ing,[t,room_num])
        answer = max(answer,room_num)

    print(answer)
    
