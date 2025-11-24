import sys
input = sys.stdin.readline
from collections import deque

def rotate(gear, d):
    if d == 1:
        gear.rotate()
    elif d == -1:
        gear.rotate(-1)
    
    return gear

if __name__ == "__main__":
    n = int(input())
    gears = []
    for i in range(n):
        line = deque(input())
        gears.append(line)

    k = int(input())
    turns = [list(map(int,input().split())) for i in range(k)]

    for s,d in turns:
        rotate_flag = [0] * n
        rotate_flag[s-1] = d
        ld = rd = d

        for i in range(s-2,-1,-1):
            if gears[i+1][6] != gears[i][2]:
                ld = -ld
                rotate_flag[i] = ld
            else: break
        
        for i in range(s,n):
            if gears[i-1][2] != gears[i][6]:
                rd = -rd
                rotate_flag[i] = rd
            else: break
        
        for i in range(n):
            gears[i] = rotate(gears[i], rotate_flag[i])

    answer = 0
    for i in range(n):
        if gears[i][0] == '1':
            answer += 1
    print(answer)