`# US022 - Add a new entry in the Agenda 
`
## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID                                     | Question: Which class is responsible for...      | Answer                | Justification (with patterns) |
|:---------------------------------------------------|:-------------------------------------------------|:----------------------|:------------------------------|
| Step 1 : Request User To Do List Entries.  		      | ...instantiating the class that handles the UI?  | AddEntryToAgendaGUI   | Pure Fabrication              |
| Step 2 : Handles the operation of retrieving list. | ..handling the operation of retrieving the list? | ToDoListController    | IE                            |
| Step 3 : Retriving the list.                       | .. retrieving the list                           | ToDoListRepository    | Repository                    |
| Step 4 : saving the entry in the agenda locally.   | ...locally saving the agenda entry?              | Agenda                | IE: owns its own data.        |
| Step 5 : saving the entry in the agenda globally.  | ...globally saving the agenda entry?             | AgendaRepository      | IE: owns all its data.        |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

Agenda
AgendaRepository


Other software classes (i.e. Pure Fabrication) identified: 




## 3.2. Sequence Diagram (SD)

![us022-sequence-diagram-full.svg](svg%2Fus022-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![us022-class-diagram.svg](svg%2Fus022-class-diagram.svg)