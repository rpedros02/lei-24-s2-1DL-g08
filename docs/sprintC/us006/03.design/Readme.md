# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer            | Justification (with patterns)                                                                                 |
|:---------------|:--------------------- |:------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     |	... interacting with the actor? | CreateVehicleUI   | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        |	... coordinating the US? | VehicleController | Controller                                                                                                    |
| 			  		        | ... knowing the user using the system?  | UserSession       | IE: cf. A&A component documentation.                                                                          |
| Step 2  		     |							 |                   |                                                                                                               |
| Step 3  		     |	...saving the inputted data? | Vehicle           | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     |	... validating all data (local validation)? | Vehicle           | IE: owns its data.                                                                                            |
| 			  		        |	... saving the created task? | VehicleRepository | IE: owns all its tasks.                                                                                       | 
| Step 5 		      |	... informing operation success?| CreateVehicleUI   | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle

Other software classes (i.e. Pure Fabrication) identified: 

* CreateVehicleUI  
* VehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.




## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)