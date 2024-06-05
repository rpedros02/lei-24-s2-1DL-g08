# Supplementary Specification (FURPS+)

## Functionality
_Specifies functionalities that: \
 (i) are common across several US/UC; \
 (ii) are not related to US/UC, namely: Audit, Reporting and Security._
* The system must prevent errors on runtime, without disrupting the user's experience.
* The system's UI must be user-friendly. 
* Login credentials and sensitive data must be encrypted and stored correctly.
* Data in the system must be available at all times and must be consistent and correctly stored.
* A list of roles with permissions is defined in the system.
* The application must also provide a general information dashboard that displays various relevant details about the green space to the user.
* The Green Space Manager must have complete, unrestricted access to all dashboards and functionalities throughout the entire application.
## Usability
_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._
* The system's manual must be available at all times.
* All error messages must be clear and correct.
* The system must include tips and a help wizard.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._
* It's important to adhere to the validation of business rules when entering or modifying data.
* Throughout the system development process, the team must embrace best practices for requirement identification and object-oriented software analysis and design. 
* They should adhere to established coding standards, such as CamelCase, and utilize Javadoc to generate comprehensive documentation for Java code.
* The development team is required to conduct unit testing for all methods, excluding those that perform Input/Output operations.
* The team should utilize the JaCoCo plugin to generate the coverage report.
* The unit tests should be implemented using the JUnit 5 framework.
* To maintain data persistence between two runs of the application, object serialization should be employed.
## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

* The application must be consistently accessible to the public, with minimal downtime whenever possible.
* The utilization of resources, including memory and CPU, is restricted to the capacity of the hosting hardware. However, efforts should be made to minimize resource usage whenever feasible.
* Response times for all tasks should be minimized to the greatest extent possible.
## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._
* The class structure should be designed to facilitate maintenance and the incorporation of new features, adhering to the best object-oriented practices.  
* The application should be easy to install on almost any personal computer.



## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

* The application documentation must be in the English language.
* Throughout system development, the team should embrace best practices for requirement identification, object-oriented software analysis and design, and adhere to recognized coding standards like CamelCase. Additionally, Javadoc should be utilized to generate comprehensive documentation for Java code.
* All the documentation must follow the standards established in the UC ESOFT.
* The graphical interface of the application should be developed using JavaFX 11.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

* The application should be developed using the Java language and can be built using either the IntelliJ IDEA or NetBeans IDEs.
* All unit tests must be created using the JUnit 5 framework.
* All methods must have corresponding unit tests, except for those involving Input/Output (I/O) operations.
### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

* The system does not require communication with any external software.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

* The system should possess some level of portability and be able to operate effectively on a relatively low-end host.






