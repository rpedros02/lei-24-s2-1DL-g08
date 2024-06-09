# Discrete Mahematics Report

## Introduction
Discrete mathematics plays a crucial role in solving complex problems across various fields, 
from computer science to civil engineering. This report addresses the application of efficient 
algorithms for constructing irrigation systems and defining emergency plans in parks. 
These tasks are fundamental to ensuring the functionality and safety of these spaces. 
Utilizing optimization techniques and computational complexity analysis is essential to achieve effective and economically viable solutions. 
We will explore five User Stories (US) that illustrate these challenges and the approaches used to solve them.

### US12- Data Import for Irrigation Routes
#### Introduction
Planning irrigation systems in parks necessitates effective resource management, with the import and structuring of data being pivotal. 
User Story 12 (US12) highlights the importance of importing a .csv file that contains details about water points and the distances between them. 
This process facilitates the creation of a unified data structure that maps out all potential routes and their corresponding installation costs. 
This foundational step is essential for implementing optimization algorithms designed to minimize the overall costs of pipe installation, thereby ensuring the development of more efficient and cost-effective irrigation systems.

### US13- Algorithm for Minimum Cost Irrigation Routes
#### Introduction
After importing the data, the next step is to apply an algorithm that identifies the minimum cost irrigation routes. 
User Story 13 (US13) focuses on implementing an algorithm that determines the routes to be opened and the pipes to be laid with a minimum accumulated cost, ensuring all water points are adequately supplied. 
This process must use only primitive operations, without resorting to existing Java library functions. 
The solution should provide a .csv file with the resulting subgraph and its total cost, along with a graphical visualization of the routes, facilitating analysis and decision-making.

### US14- Asymptotic Behavior Testing of the Algorithm
#### Introduction 
To ensure the efficiency of the algorithm developed in US13, it is essential to perform tests that evaluate its asymptotic behavior. 
User Story 14 (US14) describes the need to run the algorithm with different input sizes to observe its execution time asymptotically. 
These tests are crucial for understanding how the algorithm behaves as the complexity of the data increases. 
The analysis should include a graph illustrating the execution time as a function of input size, helping to identify potential performance limitations and areas for optimization.

### US17- Planning Evacuation Routes
#### Introduction
Safety in parks is a priority, and defining effective evacuation routes is an essential part of the emergency plan. 
User Story 17 (US17) addresses the need to create evacuation routes that lead park users to an Assembly Point using the shortest path. 
Importing a weighted cost matrix between points, defining an algorithm to find the shortest paths, and presenting the results in a visual and tabular format are critical steps in this process. 
This planning ensures that, in case of an emergency, visitors can evacuate the park quickly and safely.

### US18- Planning Evacuation Routes for Multiple Assembly Points
#### Introduction 
The complexity increases when considering multiple Assembly Points in a park. 
User Story 18 (US18) expands on the problem addressed in US17 by requiring the definition of the shortest routes to the nearest Assembly Point. 
This additional challenge requires more sophisticated algorithms that consider multiple options and choose the best route for each signaling point. 
The implementation should follow the same principles of efficiency and clarity in presenting the results, ensuring that each visitor has an optimized route to the most accessible assembly point in an emergency.

### US19- Complexity Analysis of the Algorithms
#### Introduction
Analyzing the complexity of the developed algorithms is essential to understand their limitations and ensure their efficiency in different scenarios. 
User Story 19 (US19) focuses on the theoretical worst-case time complexity analysis of the algorithms implemented in US13, US17, and US18. 
This analysis includes presenting the algorithms in pseudo-code and a detailed evaluation of the computational complexity of each procedure. 
Understanding the complexity of the algorithms helps identify potential performance bottlenecks and seek more efficient solutions, ensuring the viability of the proposed systems on a large scale.


