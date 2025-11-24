import sys
input = sys.stdin.readline

from collections import deque

if __name__ == "__main__":
    n = int(input())
    top = list(map(int,input().split()))
    stack = deque([])
    answer = [0] * n

    for i in range(n):
        while stack:
            if stack[-1][1] > top[i]:
                answer[i] = stack[-1][0] + 1
                break
            else:
                stack.pop()
            
        stack.append([i,top[i]])

    print(*answer)
