import sys
input = sys.stdin.readline


if __name__ == "__main__":
    n = int(input())
    kids = [0]
    for _ in range(n):
        kids.append(int(input()))
    
    d = [1] * (n+1)

    for i in range(1,n+1):
        for j in range(1,i):
            if kids[j]<kids[i]:
                d[i] = max(d[i],d[j]+1)
    
    print(n-max(d))