import random as r

attempts_left = 5
guess = None
random_int = r.randint(1, 20)

print("Try to guess the random integer in my head!")
while attempts_left > 0:
    print(f"Attempts left: {attempts_left} your guess?", end=" ")
    guess = int(input())
    
    if guess < random_int:
        print("Higher!")
    elif guess > random_int:
        print("Lower!")
    else:
        print("Wow! You win!")
        break

    attempts_left -= 1