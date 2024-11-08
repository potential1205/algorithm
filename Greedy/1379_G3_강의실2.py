import heapq
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    lectures = []
    for i in range(n):
        id,start,end = map(int,input().split())
        heapq.heappush(lectures,[start,end,id])

    room,ing,result = [],[],[0]*n
    for i in range(1,n+1):
        heapq.heappush(room,i)

    while lectures:
        start,end,id = heapq.heappop(lectures)

        while ing and ing[0][0] <= start:
            end_time, room_num = heapq.heappop(ing)
            heapq.heappush(room,room_num)

        room_num = heapq.heappop(room)
        heapq.heappush(ing,(end,room_num))
        result[id-1] = room_num

        
    print(max(result))
    for val in result:
        print(val)
