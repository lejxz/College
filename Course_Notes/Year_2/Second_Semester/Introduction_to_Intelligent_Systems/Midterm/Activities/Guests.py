"""
    Goal: Practice list manipulation and user input.
    Write a script that starts with an empty list called guests. Use a while loop
    to continuously ask the user for a name to add to the party list.
        ● If the user types "done", stop the loop and print the final list alphabetically.
        ● If they type a name already in the list, tell them "They're already on the list!" and don't add it again.
        ● Bonus: If the list reaches 5 names, print "The party is full!" and exit.
"""

guests = []
name = None
count = 0

while True:

    if count == 5:
        print("The party is full!", end="\n\n")
        break

    print("Enter a name to add into the party list: ", end = "")

    name = input()

    if name.lower() == "done":
        print("Finished adding guests!")
        break

    if name in guests:
        print("They're already on the list!")
        continue

    guests.append(name)

    count+=1

guests.sort()

for guest in guests:
    print(guest)