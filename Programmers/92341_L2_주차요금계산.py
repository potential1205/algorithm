import math
def convert_time(str_time):
    hour,minute = str_time.split(":")
    return int(hour)*60 + int(minute)
    
def solution(fees, records):
    answer = []
    
    result = dict()
    in_car = dict()
    
    for record in records:
        time, car_num, types = record.split(" ")
        
        if types == 'IN':
            in_car[car_num] = convert_time(time)
        else:
            in_time = in_car[car_num]
            gap = convert_time(time) - in_time
            del in_car[car_num]
            if not result.get(car_num):
                result[car_num] = []
                result[car_num].append(gap)
            else:
                result[car_num].append(gap)
    
    
    for car_num, in_time in in_car.items():
        in_time = in_car[car_num]
        gap = 1439 - in_time
        if not result.get(car_num):
            result[car_num] = []
            result[car_num].append(gap)
        else:
            result[car_num].append(gap)
    
    for key, value_list in result.items():
        cum = 0
        for value in value_list:
            cum += value
        
        if cum <= fees[0]:
            answer.append([key,fees[1]])
        else:
            rest = cum - fees[0]
            rest = math.ceil(rest/fees[2])
            answer.append([key,fees[1]+rest*fees[3]])
    
    answer.sort()
    returns = []
    for i in range(len(answer)):
        returns.append(answer[i][1])
    
    return returns