# US022 - Add a new entry in the Agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to add a new entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** When a To-Do List entry is planned and moves to the Agenda, the status change from "Pending" to "Planned". Should this entry be removed from the To-Do List or just change status to "Planned" as it is on the Agenda?
>
> **Answer:** Changing the status in the To-Do list to Planned seems to be a good approach.


> **Question:** When the GSM plans a task (that was previously in To-Do) into the Agenda, what aditional data/information does he need to input when planning?
>
> **Answer:** The starting date for the task.Later the GSM will be able to add the Team and vehicles (if required).

### 1.3. Acceptance Criteria

* **AC1:** The new entry must be associated with a green space managed by the GSM
  
* **AC2:** The new entry must exist in the To-Do list.

### 1.4. Found out Dependencies

* There is a dependency on "US21 - As a GSM, I want to add a new entry to the To-Do List." because the entry needs to be firstly placed on the To-Do list.

### 1.5 Input and Output Data

**Input Data:**


* Selected data:
    * Entry

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![us022-sequence_diagram.svg](svg%2Fus022-sequence_diagram.svg)


