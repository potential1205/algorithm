
if __name__ == "__main__":
    n = int(input())
    arr = list(map(int,input().split()))
    answer = 0

    for i in range(n):
        cnts = [0] * 100001
        for j in range(i,n):
            if cnts[arr[j]] == 0:
                cnts[arr[j]] += 1
                answer += 1
            else:
                break

    print(answer)
