# Platforma-Online-Pao

## Proiect PAO

### Platforma de e-learning

In cadrul acestui proiect am decis sa creez o platforma de e-learning pentru cursuri de informatica/matematica. Utilizatorii se pot inscrie la cursuri in functie de durata acestora, de pret si de specializare, la final primind un test final/quiz si o nota pe acesta. 

## Clase 

In cadrul acestui proiect am folosit urmatoarele clase:
```
    Course 
    Enrollment 
    MathCourse
    ProgrammingCourse
    
    Question
    Quiz
    
    User
    Teacher
    Student
    Admin

    Main

    + alte clase utilitare
```

## Comenzi aplicatie 

In cadrul aceste aplicatii se pot efectua urmatoarele actiuni 

```
    1. ADD_NEW_STUDENT NAME PHONE_NUMBER EMAIL_ADRESS
    2. ADD_NEW_TEACHER NAME PHONE_NUMBER EMAIL_ADRESS
    3. REMOVE_STUDENT ID
    4. REMOVE_TEACHER ID
    5. ADD_PROGRAMMING_COURSE NAME HOURS PRICE PROJECT_REQ NO_PROJECTS LANGUAGE TEACHER_ID
    6. ADD_MATH_COURSE NAME HOURS PRICE SUBJECT TEACHER_ID
    7. PRINT_USERS
    8. PRINT_COURSES
    9. PRINT_CHEAPEST_COURSES
    10.PRINT_QUIZ
    11.ENROLL_STUDENT ID_STUDENT ID_COURSE
    12.DISENROLL_STUDENT ID_STUDENT ID_COURSE
    13.PRINT_STUDENT_COURSES ID_USER
    14.UPDATE_TEACHER ID NEW_SALARY

```
