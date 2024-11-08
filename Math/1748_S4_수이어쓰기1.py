
if __name__ == "__main__":
    num = input()
    leng = len(num)
    val = int(num)

    answer = 0

    for i in range(leng-1):
        answer += (9*10**i)*(i+1)
        val -= (9*10**i)

    answer += (val*leng)
    
    print(answer)
