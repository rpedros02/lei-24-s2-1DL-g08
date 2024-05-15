# US005- Generate Team Proposal

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...   | Answer         | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:---------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | TeamUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | TeamController | Controller                                                                                                    |
| Step 2  		     | 	...saving the inputted data?                 | TeamRequest    | IE: object created in step 1 has its own data.                                                                |
| Step 6  		     | 	... validating all data (local validation)?  | TeamRequest    | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | Organization   | IE: knows all its tasks.                                                                                      | 
| 			  		        | 	... saving the created task?                 | Organization   | IE: owns all its tasks.                                                                                       | 
| Step 7  		     | 	... informing operation success?             | TeamUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 
* Organization
* TeamRequest

Other software classes (i.e. Pure Fabrication) identified: 
* TeamUI
* TeamController


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us005-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Data Insertion SD**

![Sequence Diagram - Partial - Data Insertion](svg/ssd-partial-1.svg)

**Creation of Team Request SD**

![Sequence Diagram - Partial - Creation of Team Request](svg/ssd-partial-2.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us005-class-diagram.svg)