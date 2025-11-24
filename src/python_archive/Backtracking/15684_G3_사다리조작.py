import sys
input = sys.stdin.readline

def check():
    for i in range(n):
        ladder = i
        for j in range(h):
            if board[j][ladder]:
               ladder+=1
            elif ladder>0 and board[j][ladder-1]:
                ladder-=1

        if ladder != i:
            return False
        
    return True

def solution(cnt,bridge,ladder):
    global ans

    if check():
        ans = min(ans,cnt)
        return
    elif cnt == 3 or ans <= cnt:
        return

    for i in range(bridge,h):
        if i == bridge:
            now = ladder
        else:
            now = 0
        
        for j in range(now,n-1):
            if not board[i][j] and not board[i][j+1]:
                if j > 0 and board[i][j-1]:
                    continue
                board[i][j] = True
                solution(cnt+1,i,j+2)
                board[i][j] = False

if __name__ == "__main__":
    n,m,h = map(int,input().split())
    board = [[False for j in range(n)] for i in range(h)]
    ans = 4

    for i in range(m):
        a,b = map(int,input().split())
        board[a-1][b-1] = True

    solution(0,0,0)
    print(ans if ans < 4 else -1)
