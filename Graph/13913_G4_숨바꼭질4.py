from collections import deque

def solution():
    queue = deque([[0,n]])
    visit = [-1] * 100001

    while queue:
        cnt,x = queue.popleft()

        if x==k:
            print(cnt)
            path = []
            while x != n:
                path.append(x)
                x = visit[x]
            path.append(n)
            print(*path[::-1])
            return

        for kx in [x-1,x+1,2*x]:
            if kx<0 or kx>100000 or visit[kx]!=-1:
                continue
            
            queue.append([cnt+1,kx])
            visit[kx] = x


if __name__ == "__main__":
    n,k = map(int,input().split())
    solution()
    