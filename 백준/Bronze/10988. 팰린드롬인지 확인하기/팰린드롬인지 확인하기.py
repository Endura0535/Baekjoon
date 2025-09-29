import sys

s = sys.stdin.readline().strip()
n = len(s)

answer = True
for i in range(n // 2):
    if s[i] != s[n - i - 1]:
        answer = False
        break

if answer:
    print(1)
else:
    print(0)
