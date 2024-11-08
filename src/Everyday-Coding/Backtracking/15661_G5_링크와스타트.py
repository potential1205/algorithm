
def cal():
    global answer

    team_0, team_1 = 0, 0

    for i in range(n):
        for j in range(n):
            if team[i] == 1 and team[j] == 1:
                team_1 += board[i][j]
            elif team[i] == 0 and team[j] == 0:
                team_0 += board[i][j]
    answer = min(answer, abs(team_0-team_1))
    return


def solution(palyer):
    if palyer == n:
        cal()
        return
    
    team[palyer] = 1
    solution(palyer+1)
    team[palyer] = 0
    solution(palyer+1)


if __name__ == "__main__":
    n = int(input())
    board = []
    for i in range(n):
        board.append(list(map(int,input().split())))

    answer = 99999
    team = [0 for i in range(n)]

    solution(0)
    print(answer)

