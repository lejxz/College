"""
    Goal: Master nested conditionals and type casting.
    Create a program that asks for a user's age and whether they have a membership card (yes/no).
        ● If they are under 18, they are "Denied."
        ● If they are 18–20, they can enter but "Cannot buy drinks."
        ● If they are 21 or older, check for the membership.
            ○ If they have a card, print "Welcome, Gold Member!"
            ○ If not, print "Standard entry granted."
"""

age = None
has_Membership = False

while age is None:
    try:
        print("What is your age? ", end="")
        age = int(input())
        if age <= 0: 
            print("Invalid age!")
            age = None
    except ValueError:
        print("Invalid input! Please enter a valid number for age.")

if age < 18:
    print("Access Denied!")
elif 18 <= age <= 20:
    print("You can enter but cannot buy drinks.")
else:
    print("Do you have membership? (yes/no): ", end="")
    has_Membership = input().strip().lower() == "yes"
    
    if has_Membership:
        print("Welcome, Gold Member!")
    else:
        print("Standard entry granted.")