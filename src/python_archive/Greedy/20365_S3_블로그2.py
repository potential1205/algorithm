

if __name__ == "__main__":
    n = int(input())
    lst = list(input())

    blue_start = ['B'] * n
    red_start = ['R'] * n

    blue_result, red_result = [],[]
    flag = True

    for i in range(n):
        if blue_start[i] != lst[i]:
            if flag == True:
                blue_result.append(1)
                flag=False

        else:
            flag=True
            continue

    flag = True

    for i in range(n):
        if red_start[i] != lst[i]:
            if flag == True:
                red_result.append(1)
                flag=False

        else:
            flag=True
            continue

    print(min(len(red_result)+1,len(blue_result)+1))