import sys
input = sys.stdin.readline

max_h = 0
max_x = 0
pli = [0 for _ in range(1001)]
for _ in range(int(input())):
    x,h = map(int,input().split())
    pli[x] = h
    if max_h < h:
        max_x = x
        max_h = h

curr = 0
answer = 0
for i in range(max_x+1):
    curr = max(curr,pli[i])
    answer += curr
curr = 0
for i in range(1000,max_x,-1):
    curr = max(curr,pli[i])
    answer += curr

print(answer)