
if __name__ == "__main__":
    n = int(input())
    answer = 0

    if n==3 or n==1:
        print(-1)
        exit()
    
    while True:
        if n % 5 != 0:
            n -= 2
            answer+=1
        else:
            answer += n//5
            break

    print(answer)