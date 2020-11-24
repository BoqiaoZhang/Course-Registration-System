# A Course Registration System

## Brief Introduction to This System


This project is designed as a course registration system. The main functions of this system were listed below. These 
functions could well serve the **students** of a university after being set up by the **university staff**.

The Main Functions of This System:
- As staff, setting the university course list
- As students, researching a course in the course lists
- As students, checking whether there are available seats for a course
- As students, registering or dropping the course 

<p>Such an idea came to my mind mainly based on three reasons. First, course registration system is close to our 
real university life. Second, course registration system is one of the most important systems in university. 
Third, I found it possible to make some improvements for the course registration systems of some universities.</p>

## User Stories
- As a staff user, I want to be able to add a course to the universityCourseList **(for grading please)**.
- As a staff user, I want to be able to remove a course from the universityCourseList **(for grading please)**.
- As a staff user, I want to be able to view all the courses that have been added to the 
university course list .
- As a staff user, I want to be able to save the current university course list.
- As a staff user, I want to be able to load the current university course list. 
- As a student user, I want to be able to search specific courses of my interests.
in the course list **(for grading please)**.
- As a student user, I want to be able to check, for the course I am interested, whether there are remaining available 
seats for me.
- As a student user, I want to be able to register a course (add a course to the registered course list), if there are
remaining available seats. 
- As a student user, I want to be able to view all the names of the courses in my 
registered course list **(for grading please)**.
- As a student user, I want to be able to drop a course from my registered course list.
- As a student user, I want to be able to save my registered course list.
- As a student user, I want to be able to load my registered course list.

##Phase 4: Task 2
I chose the second option (hierarchy). The StaffConfirmationWindow class inside ui-gui-operations-staffoperations
package is an abstract class. This abstract class has the following subclasses: StaffSavingWindow and 
StaffLoadingWindow. These subclasses both override two methods. Similarly, inside ui-gui-operations-staffoperations 
package, the StudentConfirmationWindow class is an abstract class which has four subclasses: StudentDroppingWindow,
StudentRegisteringWindow, StudentSavingWindow and StudentLoadingWindow. 

## Phase 4: Task 3
The UML diagram shows that the current version of the project has a high cohesion and a high coupling. I believe the 
following approaches can help weaken the coupling between classes:
- For class "Staff" and class "Student", create a field of the type "University".
- For "StaffConfirmationWindow" abstract class and "StudentConfirmationWindow", they still have duplicates. Hence, 
creating a new abstract class "ConfirmationWindow" can help reduce the duplicates.
- For all the class about windows, they have much duplicates. So, creating an abstract class "Window" could help.