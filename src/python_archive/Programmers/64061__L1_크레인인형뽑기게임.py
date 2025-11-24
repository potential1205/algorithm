from collections import deque

def solution(board, moves):
    answer,stack = 0, []
    n = len(board)

    for move in moves:
        for i in range(n):
            if board[i][move-1] != 0:
                stack.append(board[i][move-1])
                board[i][move-1] = 0
                break
        
        if len(stack)>=2 and stack[-1] == stack[-2]:
            stack.pop(-1)
            stack.pop(-1)
            answer+=2

    return answer