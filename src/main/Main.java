package main;


import audit.AuditService;
import models.exceptions.NoSuchTeacherException;
import service.Service;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please introduce your commands below");
        Service platform = Service.getInstance();
        AuditService audit = AuditService.getInstance();

        platform.loadData(); // load the csvs when the app starts

        while (true){
            System.out.println("Command:");
            String command = scanner.nextLine();
            String [] commandParams = command.split("\\s+");
            Menu menu_command = null;
            try {
                menu_command = Menu.valueOf(commandParams[0]);
            }
            catch(Exception e){
                System.out.println("Invalid! try again");
                continue;
            }
            switch(menu_command) {
                case ADD_NEW_STUDENT:
                    platform.addNewStudent(commandParams[1],
                            commandParams[2], commandParams[3]);
                    audit.logToCSV("ADD_NEW_STUDENT");
                    break;
                case ADD_NEW_TEACHER:
                    platform.addNewTeacher(commandParams[1],
                            commandParams[2], commandParams[3]);
                    audit.logToCSV("ADD_NEW_TEACHER");
                    break;
                case ADD_NEW_ADMIN:
                    platform.addNewAdmin(commandParams[1],
                            commandParams[2], commandParams[3]);
                    audit.logToCSV("ADD_NEW_ADMIN");
                    break;
                case REMOVE_STUDENT:
                    platform.deleteStudent(Integer.parseInt(commandParams[1]));
                    audit.logToCSV("REMOVE_STUDENT");
                    break;
                case REMOVE_ADMIN:
                    platform.deleteAdmin(Integer.parseInt(commandParams[1]));
                    audit.logToCSV("REMOVE_ADMIN");
                    break;
                case REMOVE_TEACHER:
                    platform.deleteTeacher(Integer.parseInt(commandParams[1]));
                    audit.logToCSV("REMOVE_TEACHER");
                    break;
                case PRINT_USERS:
                    platform.printAllUsers();
                    audit.logToCSV("PRINT_USERS");
                    break;
                case PRINT_CHEAPEST_COURSES:
                    platform.printCheapestCourses();
                    audit.logToCSV("PRINT_CHEAPEST_COURSES");
                    break;
                case PRINT_QUIZZ:
                    platform.printQuizzes();
                    audit.logToCSV("PRINT_QUIZZ");
                    break;
                case CREATE_QUIZ:
                    platform.createNewQuiz();
                    audit.logToCSV("CREATE_QUIZ");
                    break;
                case ADD_PROGRAMMING_COURSE:
                    int n = commandParams.length;
                    int teacherId = Integer.parseInt(commandParams[n - 1]);
                    String programmingLanguage = commandParams[n - 2];
                    int noProjects = Integer.parseInt(commandParams[n - 3]);
                    String name = commandParams[1];
                    int noHours = Integer.parseInt(commandParams[2]);
                    float price = Float.parseFloat(commandParams[3]);

                    List<String> projReq = new ArrayList<>();
                    projReq.addAll(Arrays.asList(commandParams).subList(4, n - 3));

                    try {
                        platform.addNewProgrammingCourse(name, noHours, price,
                                projReq, noProjects, programmingLanguage, teacherId);
                    }
                    catch(NoSuchTeacherException e){
                        System.out.println(e.getMessage());
                    }
                    audit.logToCSV("ADD_PROGRAMMING_COURSE");
                    break;

                case ADD_MATH_COURSE:
                    try {
                        platform.addNewMathCourse(commandParams[1],
                                Integer.parseInt(commandParams[2]),
                                Float.parseFloat(commandParams[3]),
                                commandParams[4], Integer.parseInt(commandParams[5]));
                    }
                    catch(NoSuchTeacherException e){
                        System.out.println(e.getMessage());
                    }
                    audit.logToCSV("ADD_MATH_COURSE");
                    break;
                case SET_QUIZ_MATH:
                    platform.setQuizMath(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    audit.logToCSV("SET_QUIZ_MATH");
                    break;
                case SET_QUIZ_PROGRAMMING:
                    platform.setQuizProgramming(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    audit.logToCSV("SET_QUIZ_PROGRAMMING");
                    break;
                case ENROLL_STUDENT:
                    platform.enrollStudent(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    audit.logToCSV("ENROLL_STUDENT");
                    break;
                case DISENROLL_STUDENT:
                    platform.disenrollStudent(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    audit.logToCSV("DISENROLL_STUDENT");
                    break;
                case EXIT:
                    System.out.println("Program terminated. Exiting...");
                    audit.logToCSV("EXIT");
                    return;
                default:
                    System.out.println("ok");
            }


        }
    }
}

