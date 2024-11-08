from collections import deque

def solution():
    queue = deque([[s,0]])
    visit = [False for i in range(f+1)]

    while queue:
        x,cnt = queue.popleft()

        if x == g:
            print(cnt)
            return
        
        for dx in [u,-d]:
            kx = x+dx

            if 0<kx and kx<=f and visit[kx]==False:
                queue.append([kx,cnt+1])
                visit[kx] = True

    print("use the stairs")
    return

if __name__ == "__main__":
    f,s,g,u,d = map(int,input().split())
    solution()
    