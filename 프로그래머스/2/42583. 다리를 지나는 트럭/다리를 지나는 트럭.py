def solution(bridge_length, weight, truck_weights):
    queue = [0 for i in range(bridge_length)]
    time = 0
    while(truck_weights):
        queue.pop(0)
        if sum(queue) + truck_weights[0] > weight:
            queue.append(0)
        else:
            queue.append(truck_weights.pop(0))
        time += 1
    time = time + bridge_length

    return time