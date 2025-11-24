def solution(friends, gifts):
    board = {}
    for a in friends:
        line = {}
        for b in friends:
            line[b] = 0
        board[a] = line

    for gift in gifts:
        a, b = gift.split(' ')
        board[a][b] += 1
    
    next_month, gift_index = {}, {}
    for a in friends:
        next_month[a] = 0
        gift_index[a] = 0

    for a in friends:
        for b in friends:
            gift_index[a] += board[a][b]
            gift_index[b] -= board[a][b]

    duplicate = []
    
    for a in friends:
        for b in friends:
            if a!=b and b not in duplicate:
                if board[a][b] + board[b][a] != 0 and board[a][b] != board[b][a]:
                    if board[a][b] > board[b][a]:
                        next_month[a] += 1
                    elif board[a][b] < board[b][a]:
                        next_month[b] += 1

                else:
                    if gift_index[a] > gift_index[b]:
                            next_month[a] += 1
                    elif gift_index[a] < gift_index[b]:
                        next_month[b] += 1
                        
                duplicate.append(a)
    
    return max(next_month.values())