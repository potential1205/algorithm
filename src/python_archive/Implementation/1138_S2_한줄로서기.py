
if __name__ == "__main__":
    n = int(input())
    lst = list(map(int,input().split()))
    answer = [0] * n

    for i in range(n):
        val = lst[i]
        zero_cnt = 0

        for j in range(n):
            if val == zero_cnt:
                if answer[j] == 0:
                    answer[j] = i+1
                    break
                else:
                    continue

            if answer[j] == 0:
                zero_cnt +=1
            else:
                continue

    print(*answer)