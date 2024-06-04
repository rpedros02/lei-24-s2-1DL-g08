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

## 7. Frequently Ask Questions

## 8. References