import sys

N = int(sys.stdin.readline())
i = 1
sum = 0
while sum < N:
    sum += i
    if sum > N:
        break
    i += 1

print(i - 1)
