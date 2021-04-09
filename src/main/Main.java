package main;


import service.Service;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
                default:
                    System.out.println("ok");
            }




        }
    }
}

