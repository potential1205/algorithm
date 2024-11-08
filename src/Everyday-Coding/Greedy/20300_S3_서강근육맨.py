import sys

if __name__ == "__main__":
    n = int(input())
    lst = list(map(int,input().split()))

    lst.sort()

    answer = lst[0]

    if len(lst) % 2 == 0:
        for i in range(n//2):
            answer = max(answer,lst[i]+lst[n-i-1])
    else:
        answer = lst[-1]
        for i in range(n//2):
            answer = max(answer,lst[i]+lst[n-i-2])

    print(answer)