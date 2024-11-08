import sys
input = sys.stdin.readline


if __name__ == "__main__":
    T,W = map(int,input().split())
    fruits = [0] + [int(input()) for _ in range(T)]
    dp = [[[0 for _ in range(3)] for _ in range(W+2)] for _ in range(T+1)]

    if fruits[0] == 1:
        dp[1][0][1] = 1
    else:
        dp[1][1][2] = 1
        
    for t in range(2,T+1):
        fruit = fruits[t]

        for w in range(W+1):
            if fruit == 1:
                dp[t][w][1] = max(dp[t-1][w][1],dp[t-1][w-1][2]) + 1
                dp[t][w][2] = max(dp[t-1][w-1][1], dp[t-1][w][2])
                
            else:
                dp[t][w][1] = max(dp[t-1][w][1],dp[t-1][w-1][2])
                dp[t][w][2] = max(dp[t-1][w-1][1], dp[t-1][w][2]) + 1


    max_fruit = 0
    for w in range(W+1):
        max_fruit = max(max_fruit, max(dp[T][w][1],dp[T][w][2]))
    print(max_fruit)