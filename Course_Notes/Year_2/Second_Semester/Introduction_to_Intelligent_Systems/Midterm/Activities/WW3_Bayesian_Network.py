"""
Bayesian Network: Probability of World War 3 (WW3)
===================================================
Author  : Lejuene B. Delantar
Course  : Bayesian Networks / AI
Purpose : Model the probability of WW3 based on geopolitical factors.


Variables
---------
Root nodes (independent, defined by prior probabilities only):
    RU  - Russia Mobilizes Militarily       P(Yes) = 0.35
    CN  - China Mobilizes Militarily        P(Yes) = 0.25
    US  - United States Enters Conflict     P(Yes) = 0.50
    NK  - North Korea Launches Provocation  P(Yes) = 0.20

Child nodes (dependent, defined by CPTs):
    EU  - Europe Joins the Conflict         depends on: RU, US
    NE  - Nuclear Escalation Occurs         depends on: RU, CN, NK
    WW3 - World War 3 Occurs                depends on: EU, NE, US


Inference
---------
Uses Variable Elimination to compute posterior probabilities of WW3
given observed evidence. Four queries are demonstrated:
    1. P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=No)  -> 0.6425
    2. P(WW3 | RU=Yes)                          -> 0.4153
    3. P(WW3 | RU=Yes, CN=Yes)                  -> 0.4772
    4. P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=Yes)  -> 0.7825
"""



from pgmpy.models import DiscreteBayesianNetwork
from pgmpy.factors.discrete import TabularCPD
from pgmpy.inference import VariableElimination


# --- 1. Define the Network Structure ---

model = DiscreteBayesianNetwork([
    ('RU', 'EU'),
    ('US', 'EU'),
    ('RU', 'NE'),
    ('CN', 'NE'),
    ('NK', 'NE'),
    ('EU', 'WW3'),
    ('NE', 'WW3'),
    ('US', 'WW3'),
])


# --- 2. Define CPDs ---

# P(RU): [0.65 (No), 0.35 (Yes)]
cpd_ru = TabularCPD(
    variable='RU',
    variable_card=2,
    values=[[0.65], [0.35]],
    state_names={'RU': ['No', 'Yes']}
)

# P(CN): [0.75 (No), 0.25 (Yes)]
cpd_cn = TabularCPD(
    variable='CN',
    variable_card=2,
    values=[[0.75], [0.25]],
    state_names={'CN': ['No', 'Yes']}
)

# P(US): [0.50 (No), 0.50 (Yes)]
cpd_us = TabularCPD(
    variable='US',
    variable_card=2,
    values=[[0.50], [0.50]],
    state_names={'US': ['No', 'Yes']}
)

# P(NK): [0.80 (No), 0.20 (Yes)]
cpd_nk = TabularCPD(
    variable='NK',
    variable_card=2,
    values=[[0.80], [0.20]],
    state_names={'NK': ['No', 'Yes']}
)

# P(EU | RU, US)
# Column order (pgmpy): RU=No/No/Yes/Yes  x  US=No/Yes/No/Yes
cpd_eu = TabularCPD(
    variable='EU',
    variable_card=2,
    values=[
        [0.95, 0.70, 0.45, 0.15],  # EU=No
        [0.05, 0.30, 0.55, 0.85],  # EU=Yes
    ],
    evidence=['RU', 'US'],
    evidence_card=[2, 2],
    state_names={
        'EU': ['No', 'Yes'],
        'RU': ['No', 'Yes'],
        'US': ['No', 'Yes'],
    }
)

# P(NE | RU, CN, NK)
# Column order: RU=No/No/No/No/Yes/Yes/Yes/Yes
#               CN=No/No/Yes/Yes/No/No/Yes/Yes
#               NK=No/Yes/No/Yes/No/Yes/No/Yes
cpd_ne = TabularCPD(
    variable='NE',
    variable_card=2,
    values=[
        [0.98, 0.85, 0.90, 0.70, 0.80, 0.55, 0.60, 0.25],  # NE=No
        [0.02, 0.15, 0.10, 0.30, 0.20, 0.45, 0.40, 0.75],  # NE=Yes
    ],
    evidence=['RU', 'CN', 'NK'],
    evidence_card=[2, 2, 2],
    state_names={
        'NE': ['No', 'Yes'],
        'RU': ['No', 'Yes'],
        'CN': ['No', 'Yes'],
        'NK': ['No', 'Yes'],
    }
)

# P(WW3 | EU, NE, US)
# Column order: EU=No/No/No/No/Yes/Yes/Yes/Yes
#               NE=No/No/Yes/Yes/No/No/Yes/Yes
#               US=No/Yes/No/Yes/No/Yes/No/Yes
cpd_ww3 = TabularCPD(
    variable='WW3',
    variable_card=2,
    values=[
        [0.98, 0.90, 0.75, 0.50, 0.80, 0.45, 0.35, 0.05],  # WW3=No
        [0.02, 0.10, 0.25, 0.50, 0.20, 0.55, 0.65, 0.95],  # WW3=Yes
    ],
    evidence=['EU', 'NE', 'US'],
    evidence_card=[2, 2, 2],
    state_names={
        'WW3': ['No', 'Yes'],
        'EU':  ['No', 'Yes'],
        'NE':  ['No', 'Yes'],
        'US':  ['No', 'Yes'],
    }
)

model.add_cpds(cpd_ru, cpd_cn, cpd_us, cpd_nk, cpd_eu, cpd_ne, cpd_ww3)
assert model.check_model()


# --- 3. Inference ---

infer = VariableElimination(model)

# Query: P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=No)
result = infer.query(
    variables=['WW3'],
    evidence={'RU': 'Yes', 'CN': 'Yes', 'US': 'Yes', 'NK': 'No'}
)
print("Query: P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=No)")
print(result, end="\n\n")

# Query: P(WW3 | RU=Yes)
result = infer.query(
    variables=['WW3'],
    evidence={'RU': 'Yes'}
)
print("Query: P(WW3 | RU=Yes)")
print(result, end="\n\n")

# Query: P(WW3 | RU=Yes, CN=Yes)
result = infer.query(
    variables=['WW3'],
    evidence={'RU': 'Yes', 'CN': 'Yes'}
)
print("Query: P(WW3 | RU=Yes, CN=Yes)")
print(result, end="\n\n")

# Query: P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=Yes)
result = infer.query(
    variables=['WW3'],
    evidence={'RU': 'Yes', 'CN': 'Yes', 'US': 'Yes', 'NK': 'Yes'}
)
print("Query: P(WW3 | RU=Yes, CN=Yes, US=Yes, NK=Yes)")
print(result, end="\n\n")