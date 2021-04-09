package main;


import models.exceptions.NoSuchTeacherException;
import service.Service;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please introduce your commands below");
        Service platform = Service.getInstance();

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
                    break;
                case ADD_NEW_TEACHER:
                    platform.addNewTeacher(commandParams[1],
                            commandParams[2], commandParams[3]);
                    break;
                case ADD_NEW_ADMIN:
                    platform.addNewAdmin(commandParams[1],
                            commandParams[2], commandParams[3]);
                    break;
                case REMOVE_STUDENT:
                    platform.deleteStudent(Integer.parseInt(commandParams[1]));
                    break;
                case REMOVE_ADMIN:
                    platform.deleteAdmin(Integer.parseInt(commandParams[1]));
                    break;
                case REMOVE_TEACHER:
                    platform.deleteTeacher(Integer.parseInt(commandParams[1]));
                    break;
                case PRINT_USERS:
                    platform.printAllUsers();
                    break;
                case PRINT_CHEAPEST_COURSES:
                    platform.printCheapestCourses();
                    break;
                case PRINT_QUIZZ:
                    platform.printQuizzes();
                    break;
                case CREATE_QUIZ:
                    platform.createNewQuiz();
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
                case SET_QUIZ_MATH:
                    platform.setQuizMath(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    break;
                case SET_QUIZ_PROGRAMMING:
                    platform.setQuizProgramming(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    break;
                case ENROLL_STUDENT:
                    platform.enrollStudent(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    break;
                case DISENROLL_STUDENT:
                    platform.disenrollStudent(Integer.parseInt(commandParams[1]),
                            Integer.parseInt(commandParams[2]));
                    break;
                case EXIT:
                    System.out.println("Program terminated. Exiting...");
                    return;
                default:
                    System.out.println("ok");
            }


        }
    }
}

