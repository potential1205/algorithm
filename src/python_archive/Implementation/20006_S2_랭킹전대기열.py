


if __name__ == "__main__":
    p,m = map(int,input().split())
    players = []
    for i in range(p):
        level,name = input().split(' ')
        players.append([name,int(level)])

    rooms = []

    for player in players:
        flag = False
        name, level = player
        
        for room in rooms:
            if room["min_level"] <= level <= room["max_level"] and len(room["people"]) < m:
                flag = True
                room["people"].append(player)
                if m == len(room["people"]):
                    room["state"] = "Started!"
                
                break
                
        if flag == False:
            room = dict()
            room["state"] = "Waiting!"
            room["max_level"] = level+10
            room["min_level"] = level-10
            room["people"] = [player]

            if m == len(room["people"]):
                room["state"] = "Started!"
            
            rooms.append(room)


    for room in rooms:
        print(room["state"])
        room["people"].sort()
        for j in range(len(room["people"])):
            print(room["people"][j][1],room["people"][j][0])