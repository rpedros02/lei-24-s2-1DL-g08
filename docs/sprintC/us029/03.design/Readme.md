# US029 - Record the completion of a task.

## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID | Question: Which class is responsible for...                                            | Answer                | Justification (with patterns)                                                                                                                                                 |
|:---------------|:---------------------------------------------------------------------------------------|:----------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1 		      | ... interacting with the actor?                                       | 	RecordTaskCompletionUI                    | Pure Fabrication: A UI component is used to handle interactions with the user, as there is no specific class in the domain model responsible for these interactions.          |
|                |   ... coordinating the US?                                                                                     |  RecordTaskCompletionController                                   | 	Controller: Manages the coordination of the process for recording task completion, mediating between the UI and other components.                                            |
| Step 2         | ... creating the controller?                                           | 	RecordTaskCompletionUI    | Creator: The UI is responsible for creating the instance of the controller at the start of the operation.                                                                     |
|                | 	... getting tasks of the collaborator?					                               | RecordTaskCompletionController        | Information Expert: The controller is the expert in coordinating the retrieval of tasks for the collaborator.                                                                 |
| Step 3:        |    ... retrieving the tasks for the collaborator?                                                                                    |   AgendaEntry                    | Information Expert: The AgendaEntry class is responsible for accessing and providing tasks for the collaborator.                                                              |
| Step 4:        | ... providing the tasks to the UI?                                                | RecordTaskCompletionController  | Information Expert: The controller is responsible for supplying the retrieved tasks to the UI.                                                                                |
| Step 5:        | ... selecting the task to complete?                                                              | Collaborator	 | Information Expert: The collaborator selects the task to be completed.                                                                                                        |
| Step 6:        | ... providing the finish time for the task?                                                |           RecordTaskCompletionUI           | 	Controller: The UI collects and passes the finish time provided by the collaborator to the controller.                                                                                                                                                                             |
| Step 7:        | ... marking the task as completed?                                                                                     | RecordTaskCompletionController        | Controller: The controller coordinates marking the task as completed with the finish time.                                                                                                                                                             |
| Step 8:        |    ... removing the task from the repository?                                                                                                                  |  AgendaEntry                      |    Information Expert: The AgendaEntry class is responsible for initiating the removal of the task from the repository.                                                                                                                                                                           |
|                |     ... handling the success of the task removal?                                                                                                                 |  AgendaEntryRepository                      |    Information Expert: The repository is responsible for confirming the removal of the task.                                                                                                                                                                           |
| Step 9:        |    ... providing the success response to the controller?                                                                                                                  | AgendaEntry                       |    Information Expert: The AgendaEntry class communicates the success of the operation to the controller.                                                                                                                                                                           |
| Step 10:       |     	... informing the UI of the success of the operation?                                                                                                                 |  RecordTaskCompletionController                      |     Information Expert: The controller passes the success information to the UI.                                                                                                                                                                          |
| Step 11:       |      ... displaying the success message to the collaborator?                                                                                                                |  RecordTaskCompletionUI                      |    	Information Expert: The UI is responsible for displaying the success message to the collaborator.                                                                                                                                                                           |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Task 

Other software classes (i.e. Pure Fabrication) identified:

* CompleteTaskUI
* CompleteTaskController


## 3.2. Sequence Diagram (SD)



### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us029-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us029-class-diagram.svg)