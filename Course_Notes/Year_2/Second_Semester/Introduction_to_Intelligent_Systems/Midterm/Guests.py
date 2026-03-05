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