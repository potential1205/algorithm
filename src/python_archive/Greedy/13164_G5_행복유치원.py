import sys
input = sys.stdin.readline

def solution():
    gaps = []
    for i in range(1,len(kids)):
        gaps.append(kids[i] - kids[i-1])

    gaps.sort(reverse=True)

    print(sum(gaps[k-1:]))

if __name__ == "__main__":
    n,k = map(int,input().split())
    kids = list(map(int,input().split()))
    solution()