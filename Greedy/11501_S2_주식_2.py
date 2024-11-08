
if __name__ == "__main__":
    t = int(input())

    for _ in range(t):
        n = int(input())
        lst = list(map(int,input().split()))
        answer = 0
        max_val = lst[-1]
        for i in range(n-1,-1,-1):
            if max_val > lst[i]:
                answer += (max_val-lst[i])
            else:
                max_val = lst[i]

        print(answer)