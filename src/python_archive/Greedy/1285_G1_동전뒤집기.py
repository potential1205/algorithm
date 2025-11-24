import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    board = [list(input()) for i in range(n)]
    answer=n*n
 
    for bit in range(1<<n):
        tmp=[board[i][:] for i in range(n)]
        for i in range(n):
            if bit & (1<<i):
                for j in range(n):
                    if tmp[i][j]=='T':
                        tmp[i][j]='H'
                    else:
                        tmp[i][j]='T'
        
        cnt=0
        for i in range(n):
            cnt_t=0
            for j in range(n):
                if tmp[j][i]=='T':
                    cnt_t+=1
            cnt+=min(cnt_t,n-cnt_t)
        answer=min(cnt,answer)

    print(answer)