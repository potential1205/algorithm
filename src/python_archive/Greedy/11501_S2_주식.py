import sys
input = sys.stdin.readline

if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n = int(input())
        lst = list(map(int,input().split()))
        lst.reverse()
        max_val = lst[0]
        answer = 0

        for i in range(1,n):
            if max_val > lst[i]:
                answer += (max_val-lst[i])
            else:
                max_val = lst[i]
        
        print(answer)
