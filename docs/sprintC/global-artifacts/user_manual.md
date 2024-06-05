# User Manual Folder
## 1. Glossary
 [Click here to view the Glossary](01.requirements-engineering/glossary.md)
 
## 2. Introduction
Green spaces, such as parks and gardens, are crucial for enhancing the quality of life in urban and semi-urban areas. 
As urbanization continues to rise globally, with predictions indicating that 68% of the world population will reside in urban areas by 2050, the effective management of these green spaces becomes increasingly important. 
This integrative project aims to address the planning, construction, and maintenance of urban green spaces by developing a comprehensive software solution for MusgoSublime (MS), an organization dedicated to managing such spaces.

Students are tasked with creating a computer system to streamline various operations of MS, focusing on aspects such as team management, allocation of resources, optimization of irrigation and lighting systems, and performance measurement through statistical indicators. 
Leveraging their knowledge from courses like Software Engineering (ESOFT), Programming Paradigms (PPROG), Computational Mathematics (MATCP), Discrete Mathematics (MDISC), and Laboratory-Project II (LAPR2), students will employ an agile methodology, specifically SCRUM, to manage the iterative and incremental development process of the project.

The resulting software solution, composed of Java applications, must adhere to the principles of Test-Driven Development (TDD) to ensure maintainability and high-quality code. 
The project not only challenges students to apply theoretical concepts in a practical setting but also aims to deliver a proof-of-concept that meets the real-world requirements provided by MS, thereby contributing to the sustainable management of urban green spaces.

## 3. System Requirements


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


   1.2. Register a job | HRM

   As an HRM, I want to register a job that a collaborator need to have.


   1.3. Register a collaborator | HRM

   As an HRM, I want to register a collaborator with a job and fundamental characteristics.
 

   1.4. Assign skills to a collaborator | HRM
 
   As an HRM, I want to assign one or more skills to a collaborator.
 

   1.5. Generate team proposal | HRM

   As an HRM, I want to generate a team proposal automatically.


   1.6. Register a vehicle | FM

   As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross      Weight, Current Km, Register Date, Acquisition Date, Maintenance/Check-up Frequency (in Kms).


   1.7. Register a vehicle’s check-up | FM

   As an FM, I want to register a vehicle’s check-up.


   1.8. List the vehicles in need off check-up | FM

   As an FM, I want to list the vehicles needing the check-up.


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