# US24 - Postpone an entry in the Agenda to a specific future date. 


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to Postpone an entry in the Agenda to a
specific future date.

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** he date you want to postpone in this US24 is the date referring to the "approximate expected duration" field when we registered the task?
>
> **Answer:** No; sometimes, for various reasons (e.g. insufficient staff, faulty equipment or adverse weather conditions) a task has to be postponed to a new date; task duration is not directly related to this.

> **Question:** Are there acceptance criteria when asking for the list?
>
> **Answer:** The list must clearly identify the vehicles through: plate number, brand, model and the that justified the checkup need.

### 1.3. Acceptance Criteria

* **AC1:** When a user requests the list of vehicles needing check-up, the system should display the following information for each vehicle:

    * Plate number
    * Brand 
    * Model
    * Reason for check-up need
  
* **AC2:** The system should calculate the vehicles needing check-up based on the following criteria:

  * If the current kilometers of a vehicle exceed its maintenance/check-up frequency.

* **AC3:** The FM must be able to sort out the list.
* **AC4** The list should be easy to understand and navigate, facilitating quick identification of vehicles needing attention.

### 1.4. Found out Dependencies

* There is a dependency on "US007 - Register vehicle for check-up" as there must be at least one vehicle to create a list.

### 1.5 Input and Output Data
	
* Selected data:
    * order in which to show the vehicles. 

**Output Data:**

* List of existing vehicles needing check-up and their information.
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![us024](svg/us024-sequence_diagram.svg)


