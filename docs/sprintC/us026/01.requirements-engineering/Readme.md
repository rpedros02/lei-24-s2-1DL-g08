# US026 - Assign one or more vehicles to an entry in the Agenda.


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign one or more vehicles to an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> "Green Spaces Manager (GSM) - the person responsible for managing the green spaces in charge of the organization."

> "The Agenda is a crucial mechanism for planning the week’s work."

> "Vehicles are needed to carry out the tasks assigned to the teams and to
transport machines and equipment. This type of vehicle may be only for
passengers or mixed, light or heavy, open box or closed vans or trucks."


**From the client clarifications:**

> **Question:** Todos os veículos da empresa devem estar disponíveis para os atribuir a uma entrada da agenda, ou só os veículos com a manutenção em dia?
> 
>
> **Answer:** Todos os veiculos que não estejam assignados a uma tarefa no mesmo periodo.
Num contexto real precisariamos de gerir também (in)disponibilidade dos veiculos por revisões ou avarias mas não é necessário nesta prova-de-conceito.

> **Question:** What are the criteries to accept a assign of a Vehicle to a Entry?
Only Vehicle with no Entry's can be assigned ?
Only Vehicles with no Entry on the day selected ?
It is possible to add any kind of vehicles?
>
> **Answer:** The vehicle needs to be available in the period.
Yes, any can of vehicles can be assigned.

### 1.3. Acceptance Criteria

* **AC1:** When a user requests the list of vehicles the system should display the following information for each vehicle:

    * Plate number
    * Brand 
    * Model
    * Type
    * Current Km
  
* **AC2:** Vehicle Selection Interface

  * The system must provide a user interface where the GSM can select vehicles from a list.
  * The interface should allow the selection of multiple vehicles for a single agenda entry.

* **AC3:** Validation of Vehicle Availability
  * The system must validate that the selected vehicles are available (not already assigned to another task) during the specified time interval of the agenda entry.
  * If a vehicle is unavailable, the system should notify the GSM and prevent the assignment.

* **AC4** Vehicle Compatibility Check
  * The system must check and ensure that the selected vehicles are compatible with the task requirements of the agenda entry.
  * Compatibility factors include the type of vehicle required and any specific equipment or modifications needed for the task.


### 1.4. Found out Dependencies

* 

### 1.5 Input and Output Data
	
**Input data:**

* Agenda Entry Details
* Vehicle Details

**Output Data:**

* Assignment Confirmation
* Assigned Vehicles List

### 1.6. System Sequence Diagram (SSD)

![us026](svg/us026-sequence_diagram.svg)


