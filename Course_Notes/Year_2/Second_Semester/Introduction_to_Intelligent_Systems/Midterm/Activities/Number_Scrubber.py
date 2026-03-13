"""
    Goal: Combine loops, lists, and math logic.
    Start with a list of numbers: data = [12, 45, -3, 0, 101, 22, 7, 88, -15]. Loop through the list and create two new lists:
        1. evens: Should contain all even numbers from the original list.
        2. cleaned: Should contain only positive numbers (greater than 0).
    Challenge: At the end, print the average of the cleaned list.
"""

data = [12, 45, -3, 0, 101, 22, 7, 88, -15]
evens_data = []
cleaned_data = []
average = None

for i in range (len(data)):
    if data[i] % 2 == 0:
        evens_data.append(data[i])
    if data[i] > 0:
        cleaned_data.append(data[i])

if cleaned_data:
    average = sum(cleaned_data) / len(cleaned_data)

print("Evens: ", evens_data)
print("Cleaned data: ", cleaned_data)
print("Average of cleaned list: ", average)