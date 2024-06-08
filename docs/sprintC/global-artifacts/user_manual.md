# User Manual Folder
## 1. Glossary
 [Click here to view the Glossary](01.requirements-engineering/glossary.md)
 
## 2. Introduction
This user manual aims to provide instructions to help users understand how to use the Green Spaces Management Application.
Firstly, it is important to know the main goal of our app: to create a good environment in green spaces by adopting the best management practices and providing a friendly and healthy environment.
To achieve our goal, this user manual offers an understanding of the functionalities, usage guidelines, and features of the application.
As expected, this manual will be divided into various chapters, such as "System Overview," "Features," "Glossary," and others. The way this manual is structured will help users solve any problems they might encounter, learn how to perform each task, provide step-by-step explanations of the features and their user stories, and include a FAQ section to showcase feedback received.
The structure of this manual is designed to assist users in resolving any issues they may face, learning how to perform each task, explaining the features and their user stories step by step, and providing FAQs to allow users to see the feedback received.

## 3. System Requirements

1.	Operating System
      •	Windows:
- Windows 10, Windows 8.1, Windows 8, Windows 7, Windows Vista
  •	macOS:
- macOS High Sierra, macOS Sierra, macOS El Capitan
  •	Linux:
- Ubuntu 20.04 LTS, Ubuntu 18.04 LTS, Ubuntu 16.04 LTS

2.	Browsers:
      •	Google Chrome:
- Version 80 or higher
  •	Mozilla Firefox:
- Version 78 or higher
  •	Microsoft Edge:
- Version 80 or higher

2. Java Platform
   •	Minimum Java Version: Java SE 19 or higher

3. Graphics Support
- Prism Hardware Pipeline:
  •	NVIDIA:
- Mobile GPUs: GeForce 8M and 100M series or higher
- Desktop GPUs: GeForce 8 and 100 series or higher
- Workstation GPUs: Quadro FX 300 series or higher
  •	ATI:
- Mobile GPUs: Mobility Radeon HD 3000, 4000, and 5000 series
- Desktop GPUs: Radeon HD 2400, 3000, 4000, 5000, and 6000 series
  •	Intel:
- Mobile GPUs: GMA 4500MHD and GMA HD
- Desktop GPUs: GMA 4500 and GMA HD

4. JavaFX Installation
   •	JavaFX SDK and Runtime:
- Included in the JDK starting with Java SE 7 Update 2
  •	Standalone JavaFX SDK:
- For JDK older than 7u2
  •	Standalone JavaFX Runtime:
- For JDK older than 7u2 and no plan to install the JavaFX SDK

5. NetBeans IDE
   •	Required Version: NetBeans IDE 7.1 (at least a prerelease version)

6. Additional Requirements
   •	Internet Connection: Required for accessing online features and updates
   •	Java 19 or Higher: Required for JavaFX 20
   •	org.apache.commons.math3.stat Package: Required for JavaFX 20

## 4. System Overview

Our software system, crafted with Java classes and user interfaces (UIs), is designed to enhance the management and oversight of natural parks and green spaces.

Key Features
1.	Employee Management
      •	Details Recording: Store personal and contact information for park employees.
      •	Role Tracking: Monitor roles and responsibilities to improve task assignment and team management.
      •	Skills & Jobs: Create and assign skills and jobs to collaborators, facilitating optimal team formation.

2.	Vehicle Management
      •	Data Logging: Register and track maintenance records, registration, and mileage of vehicles.
      •	Maintenance Tracking: Log maintenance checkups and view vehicles needing attention.

3.	User Roles and Permissions
      •	Access Control: Customize access levels based on user roles, ensuring relevant feature and information access.

4.	Park Resource Management
      •	Resource Tracking: Efficiently manage and track equipment, facilities, and natural assets.

5.	Statistical Analysis
      •	Performance Indicators: Analyze water and energy consumption, equipment usage, and visitor demographics to support data-driven decisions.

6.	Irrigation System Management
      •	Data Import: Import .csv files with Water Point X, Water Point Y, and Distance data.
      •	Cost Optimization: Apply algorithms to determine optimal routes and necessary pipes for minimal water supply cost.

7.	Data Analysis
      •	Cost Forecasting: Predict monthly water consumption costs for new parks using linear regression.
      •	Trend Analysis: Determine best-fit lines using polynomial regression.

8.	Emergency Planning
      •	Evacuation Routes: Install signs indicating the shortest routes to an Assembly Point during emergencies.

9.	Task Management
      •	Task Organization: Register green spaces, add entries to To-Do List and Agenda, assign teams, reschedule or cancel tasks, and list managed green spaces.
      •	Task Tracking: Collaborators can view assigned tasks within specific dates and record task completion.

User Interface
The system features a user-friendly interface, making it accessible to users of all technical skill levels, and includes robust security measures to protect sensitive information.

Development Approach
Our software is a comprehensive solution designed to enhance green space management efficiency and effectiveness. Developed iteratively, with initial features outlined in the first project sprint and subsequently implemented using Java classes and UIs, the system is continuously refined and improved to ensure robustness and user-friendliness.



## 5. Features

1. Esoft

   
   1.1. Register a skill | HRM

   As a Human Resources Manager (HRM), I want to register skills that a collaborator may have.
   Here is how our system will handle this process, step by step:

•	The HRM requests to register a skill;
•	The system displays a list of available skills;
•	While the HRM wants to select more skills:
      o	The HRM selects a skill from the list;
      o	The system prompts for confirmation of the selected skill;
      o	The HRM confirms the selection;
      o	The system saves the skill to the list; 
      o	This loop continues until all relevant skills for the collaborator are selected;
•	The system displays a message indicating the operation was successful.


   1.2. Register a job | HRM

   As an HRM, I want to register a job that a collaborator need to have.
   Here is how our system will handle this process, step by step:

•	The HRM requests to register a job;
•	The system displays a list of available jobs;
•	The HRM selects a job from the list;
•	The system prompts for confirmation of the selected job;
•	The HRM confirms the selection;
•	The system assigns the job to the collaborator;
•	The system displays a message indicating the operation was successful.


   1.3. Register a collaborator | HRM

   As an HRM, I want to register a collaborator with a job and fundamental characteristics.
   Here is how our system will handle this process, step by step:


•	The HRM requests to register a collaborator;
•	The system displays a list of available jobs;
•	The HRM selects a job from the list;
•	The system prompts for the collaborator's data;
•	The HRM inputs the required data;
•	The system displays a message indicating the operation was successful.
 

   1.4. Assign skills to a collaborator | HRM
 
   As an HRM, I want to assign one or more skills to a collaborator.
   Here is how our system will handle this process, step by step:
   •	The HRM accesses the collaborator's profile;
   •	The system displays the profile;
   •	The HRM selects the option to manage skills;
   •	The system shows the list of predefined skills and the option to add more;
   •	The HRM either selects from the predefined skills or chooses to add new skills:
       o	If the HRM chooses to add skills, the system prompts to enter the skill;
       o	The system asks for confirmation;
       o	The HRM confirms the addition;
   •	The system displays a message indicating the operation was successful.
 

   1.5. Generate team proposal | HRM

   As an HRM, I want to generate a team proposal automatically.
   Here is how our system will handle this process, step by step:
   •	The HRM requests to generate a team proposal automatically;
   •	The system prompts for the team data;
   •	The HRM inputs the requested data;
   •	In a loop:
       o	The system displays a list of employees with their respective skills and jobs;
       o	The HRM selects an employee from the list;
       o	The system adds the selected employee to the team;
       o	If the team size is still smaller than the requested size, the loop repeats;
   •	When the team is full, the system displays a message indicating the operation was successful.


   1.6. Register a vehicle | FM

   As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Check-up Frequency (in Kms).
   ere is how our system will handle this process, step by step:
   •	The FM requests to register a vehicle;
   •	The system prompts for the required data;
   •	The FM inputs the data;
   •	The system verifies if all necessary information is provided and asks the FM to confirm it;
   •	The FM confirms the information;
   •	Once the registration is complete, the system displays a message indicating the operation was successful.


   1.7. Register a vehicle’s check-up | FM

   As an FM, I want to register a vehicle’s check-up.
   Here is how our system will handle this process, step by step:
   •	The FM requests to register a vehicle's check-up;
   •	The system prompts for the necessary data;
   •	The FM inputs the requested data;
   •	The system displays a message indicating the operation was successful.


   1.8. List the vehicles in need off check-up | FM

   As an FM, I want to list the vehicles needing the check-up.
   Here is how our system will handle this process, step by step:
   •	The FM requests the list of vehicles needing a check-up;
   •	The system displays the list;
   •	The FM requests to filter the list by location;
   •	The system displays the filtered list.


   1.20. Register a Green Space | GSM

   As a Green Space Manager (GSM), I want to register a green space (garden, medium-sized park, or large-sized park) and its respective area.
   Here is how our system will handle this process, step by step:
   •	The GSM requests to register a green space;
   •	The system prompts for the required data;
   •	The GSM inputs the data;
   •	The system displays a message indicating the operation was successful.


   1.21. Add a new entry to the To-Do list | GSM

   As a Green Space Manager (GSM), I want to add a new entry to the To-Do List.
   Here is how our system will handle this process, step by step:
   •	The GSM requests to register an entry on the To-Do list;
   •	The system prompts for the required data;
   •	The GSM inputs the data;
   •	The system displays a message indicating the operation was successful.


   1.22. Add a new entry in the agenda | GSM

   As a Green Space Manager (GSM), I want to add a new entry in the Agenda.
   Here is how our system will handle this process, step by step:
   •	The GSM requests to register an entry on the Agenda;
   •	The system displays the list of entries from the To-Do list;
   •	The GSM selects an entry;
   •	The system prompts for the starting date;
   •	The GSM inputs the starting date;
   •	The system displays a message indicating the operation was successful.


   1.23. Assign a team to an entry in the Agenda | GSM

   As a Green Space Manager (GSM), I want to assign a Team to an entry in the Agenda.
   Here is how our system will handle this process, step by step:
   •	The GSM asks to assign a Team to an entry in the Agenda;
   •	The system shows the list of entries from the Agenda;
   •	The GSM selects an entry;
   •	The system shows the list of Teams;
   •	The GSM selects a team;
   •	The system shows a message of operation success;


   1.24. Postpone an entry in the agenda | GSM

   As a Green Space Manager (GSM), I want to Postpone an entry in the Agenda to a specific future date.
   Here is how our system will handle this process, step by step:
   •	The Green Space Manager (GSM) requests to postpone an entry on the Agenda.
   •	The system displays the list of entries available in the Agenda.
   •	The GSM selects the entry they wish to postpone.
   •	The system prompts the GSM to input the new starting date for the selected entry.
   •	The GSM inputs the desired starting date for postponement.
   •	The system confirms the successful postponement of the entry with a notification message.


   1.25. Cancel an entry in the agenda | GSM

   As a Green Space Manager (GSM), I want to Cancel an entry in the Agenda.
   Here is how our system will handle this process, step by step:
   •	The Green Space Manager (GSM) requests to cancel an entry on the Agenda.
   •	The system displays the list of entries available in the Agenda.
   •	The GSM selects the entry they wish to cancel.
   •	The system confirms the successful cancellation of the entry with a notification message.


   1.26. Assign one or more vehicles to an entry in the agenda | GSM

   As a Green Space Manager (GSM), I want to assign one or more vehicles to an entry in the Agenda.
   Here is how our system will handle this process, step by step:
   •	The Green Space Manager (GSM) requests to assign a vehicle to an entry in the Agenda.
   •	The system displays the list of entries available in the Agenda.
   •	The GSM selects the desired entry from the provided list.
   •	The system presents the GSM with the list of available vehicles.
   •	The GSM selects one or more vehicles to assign to the selected entry.
   •	The system confirms the successful assignment with a notification message.


   1.27. List all green spaces managed  by me | GSM

   As a Green Space Manager (GSM), I need to list all green spaces managed by me.
   Here is how our system will handle this process, step by step:
   •	The Green Space Manager (GSM) requests to see the list of green spaces managed by them.
   •	The system retrieves and displays the list of green spaces managed by the GSM.


   1.28. List all green spaces managed by me |  Collaborator

   As a Collaborator, I wish to consult the tasks assigned to me between two dates.
   Here is how our system will handle this process, step by step:
   •	The Collaborator requests to consult the tasks assigned to them between two dates.
   •	The system prompts the collaborator to input the first date.
   •	The collaborator inputs the first date as requested.
   •	The system then prompts the collaborator to input the last date.
   •	The collaborator inputs the last date.
   •	The system retrieves and displays all tasks assigned to the collaborator between the two input dates.


   1.29. Record the completion of the task | Collaborator

   As a Collaborator (CLB), I want to record the completion of a task.
   Here is how our system will handle this process, step by step:
   •	The Collaborator  requests to record the completion of a task on the Agenda.
   •	The system displays the list of entries available in the Agenda.
   •	The collaborator selects the entry/task for which they want to record completion.
   •	The system confirms the successful recording of task completion with a notification message.


2. MATCP

   
   2.1. US09 - Know the exact costs referring to water consumption of specific green space | GSM

   This is a request for statistical analysis of water consumption costs in green parks, based on a provided CSV file. The objectives include creating a monthly bar plot of water consumption, calculating the average monthly water consumption costs for each park, and comparing statistical indicators between the park with the highest and lowest water consumption. This includes calculating the mean, median, standard deviation, and skewness coefficient, constructing relative and absolute frequency tables, checking for outliers, and graphically representing the data through histograms with 10 and 100 classes.
   

   2.2. US10 - Know which piece(s) of equipment is/are used in each day | GSM
   
   This is a request to analyze equipment usage data from a park, where users indicate the equipment they used each day. The provided CSV file contains the choices of 1000 users. The goal is to create a pie chart showing the percentage of usage for each piece of equipment, such as walking trails, picnic areas, and exercise gymnastics equipment.
   

   2.3. US11 - Be able to collect data from the user portal about the use of the park 
 
   This is a request to analyze park usage data across different age groups. The data, stored in a CSV file, includes age (categorical), park recommendation (binary), and monthly visit frequency (numeric). The analysis involves calculating the proportion of users recommending the park within each age group and creating boxplots to visualize the distribution of monthly visit frequency by age group.


3. MDISC

   
   3.1. US12 - Import a .csv file | GSM

   Import a .csv file with water point coordinates (X, Y) and distances between them into a unified data structure, representing all possible routes for laying pipes, along with their installation costs.
   

   3.2. US13 - Apply an algorithm that returns the routes to be opened and pipes needed to be laid with a minimum accumulated cost | GSM
   
   Develop an algorithm to find optimal routes for laying pipes in a garden network, minimizing costs while ensuring adequate water supply. The output should include a .csv file with the output subgraph and total cost, along with visualizations of the input and output graphs. Additionally, provide documentation detailing the implemented procedures.


   3.3. US14 - Run tests for inputs of variable size | QAM

   Run tests on the US13 algorithm with inputs of varying sizes to observe its execution time behavior. Deliverables include a .csv file containing input size and execution time data for 30 examples, along with an image file showing the execution time graph for this data.


## 6.Troubleshooting

Users may encounter the following common issues while using the software system:
•	Login Issues: Ensure your username and password are entered correctly if you cannot log in.
•	Connection Issues: Check your network settings and connection if you are unable to access the internet.
•	Performance Issues: Verify that your hardware and software meet the system requirements if the system is running slowly.
•	Data Loss: Review your backup settings and restore data from a backup if necessary.
•	Security Issues: Examine your firewall and antivirus settings if you experience security concerns.
•	Compatibility Issues: Confirm that your software and hardware meet the compatibility requirements.
•	Printing Issues: Inspect your printer settings and connections if you cannot print documents.
•	Database Issues: Verify your database settings and connections if you encounter problems with the database.
•	UI Issues: Check your display settings and resolution if you experience user interface issues.
•	Other Issues: For any other problems, refer to the user manual or contact customer support for assistance.

If the issue persists, contact the technical support team for assistance.
Contacts: 1231131@isep.ipp.pt 1231287@isep.ipp.pt 1231261@isep.ipp.pt 1231501@isep.ipp.pt

## 7. Frequently Ask Questions

•	Purpose and Functionality
What is the purpose of the software system?
The software system is designed to optimize the administration and monitoring of natural parks and other green spaces. Its primary purpose is to ensure efficient management and maintenance of these areas.
•	Target Audience
Who are the primary users of the software system?
The primary users of the software system include Green Space Managers (GSMs), Fleet Managers (FMs), Quality Assurance Managers (QAMs), and other stakeholders who require efficient management and monitoring of green spaces.
•	Key Features
What are the key features of the software system?
The software system includes several key features that support the efficient administration and monitoring of green spaces. These features include employee management, vehicle management, user roles and permissions, park resource management, statistical analysis, irrigation system management, data analysis, and emergency planning.
•	Hardware Requirements
What are the hardware requirements for running the software system?
To run the software system, users need a standard desktop or laptop computer with specific hardware requirements. These include an Intel Core i3 or equivalent processor, 4GB RAM, 500GB HDD or equivalent storage, a 15-inch monitor with a resolution of 1366x768 pixels, keyboard and mouse, internet connection, printer, and scanner.
•	Software Requirements
What are the software requirements for running the software system?The software system requires specific software to run. These include Windows 10, macOS 10.15, Ubuntu 20.04 LTS operating systems, Java Runtime Environment (JRE) version 8 or higher, web browser (Google Chrome, Mozilla Firefox, Microsoft Edge), database (MySQL, PostgreSQL, SQLite), and development environment (IntelliJ IDEA, Eclipse, NetBeans).
•	Network Requirements
What are the network requirements for running the software system?The software system requires a broadband or high-speed internet connection, network security (firewall, antivirus software), VPN (optional), and standard network security protocols and software.
•	Common Issues
What are the common issues that users may encounter while using the software system?Users may encounter several common issues while using the software system, including login issues, connection issues, performance issues, data loss, security issues, compatibility issues, printing issues, database issues, and UI issues.
•	Troubleshooting Tips
What are the troubleshooting tips for common issues that users may encounter while using the software system?To troubleshoot common issues, users can follow these tips: check username and password for errors, network settings and connection, hardware and software requirements, backup settings and restore from a backup, firewall and antivirus settings, software and hardware requirements, and printer settings and connections.

## 8. References