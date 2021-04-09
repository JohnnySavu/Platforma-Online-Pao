package service;

import models.courses.Course;
import models.courses.MathCourse;
import models.courses.ProgrammingCourse;
import models.exceptions.NoSuchTeacherException;
import models.quiz.Question;
import models.quiz.Quiz;
import models.users.Admin;
import models.users.Student;
import models.users.Teacher;
import models.users.User;

import java.util.*;

public class Service {
    private TreeSet<Course> courseSet;
    private TreeSet<User> userSet;
    private List<Quiz> quizzes;

    public static Service instance;

    public static Service getInstance(){
        if (instance == null)
            instance = new Service();
        return instance;
    }

    private Service(){
        this.userSet = new TreeSet<User>();
        this.courseSet = new TreeSet<Course>();
        this.quizzes = new ArrayList<Quiz>();
    }


    public void addNewStudent(String nume, String phoneNumber, String email){
        Student aux = new Student(nume, phoneNumber, email);
        userSet.add(aux);
    }

    public void addNewTeacher(String nume, String phoneNumber, String email){
        Teacher aux = new Teacher(nume, phoneNumber, email);
        userSet.add(aux);
    }

    public void addNewAdmin(String nume, String phoneNumber, String email){
        Admin aux = new Admin(nume, phoneNumber, email);
        userSet.add(aux);
    }

    public void deleteAdmin(int id){
        Admin admin = getAdminById(id);
        if (admin == null)
            System.out.println("No such admin");
        else
            userSet.remove(admin);
    }

    public void deleteStudent(int id){
        Student student = getStudentById(id);
        if (student == null)
            System.out.println("No such student");
        else
            userSet.remove(student);
    }

    public  void deleteTeacher(int id){
        Teacher teacher = getTeacherById(id);
        if (teacher == null)
            System.out.println("No such teacher");
        else
            userSet.remove(teacher);
    }

    private Quiz getQuizById(int id){
        for (Quiz q : this.quizzes)
            if (id == q.getId()) {
                return q;
            }
        return null;
    }

    private Admin getAdminById(int id){
        for (User user : userSet)
            if (user instanceof Admin && user.getId() == id)
                return (Admin)user;
        return null;
    }

    private Student getStudentById(int id){
        for (User user : userSet)
            if (user instanceof Student && user.getId() == id)
                return (Student)user;
        return null;
    }

    private Teacher getTeacherById(int id){
        for (User user : userSet)
            if (user instanceof Teacher && user.getId() == id)
                return (Teacher)user;
        return null;
    }

    public void addNewMathCourse(String name, int noHours, float price, String subject, int teacherId)
                                                                        throws NoSuchTeacherException {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher == null)
            throw new NoSuchTeacherException("No teacher with this id");

        Course newCourse = new MathCourse(name, noHours, price, subject, teacher);
        courseSet.add(newCourse);
    }

    public void addNewProgrammingCourse(String name, int noHours, float price, List<String> projectsRequirments,
                                        int noProjects, String programmingLanguage, int teacherId) throws NoSuchTeacherException {

        Teacher teacher = getTeacherById(teacherId);
        if (teacher == null)
            throw new NoSuchTeacherException("No teacher with this id");

        Course newCourse = new ProgrammingCourse(name, noHours, price, projectsRequirments,
                                                  noProjects, programmingLanguage, teacher);

        courseSet.add(newCourse);
    }

    public void createNewQuiz(){
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.println("Introduce the number of questions you would like for this quiz:");

        while (true) {
            try {
            n = Integer.parseInt(scanner.nextLine());
            } catch (final NumberFormatException e) {
                System.out.println("Not a number. Try again");
                continue;
            }
            break;
        }
        List<Question> questionList = new ArrayList<Question>();

        for (int k = 0; k < n; ++k) {
            System.out.println("Introduce the question\n");
            String question = scanner.nextLine();
            List<String> answers = new ArrayList<String>();
            Set<Integer> correctAnswers = new TreeSet<Integer>();

            for (int i = 0; i < n; ++i) {
                System.out.println("Introduce answer nb " + i);
                String answer = scanner.nextLine();
                answers.add(answer);
            }
            System.out.println("Introduce the number of correct answers:");
            int no;
            while (true) {
                try {
                    no = Integer.parseInt(scanner.nextLine());
                } catch (final NumberFormatException e) {
                    System.out.println("Not a number. Try again");
                    continue;
                }
                break;
            }
            for (int i = 0; i < no; ++i) {
                System.out.println("Introduce the " + i + " correct answer");
                int aux;
                while (true) {
                    try {
                        aux = Integer.parseInt(scanner.nextLine());
                    } catch (final NumberFormatException e) {
                        System.out.println("Not a number. Try again");
                        continue;
                    }
                    break;
                }
                correctAnswers.add(aux);
            }
            Question auxQuestion = new Question(question, answers, correctAnswers);
            questionList.add(auxQuestion);
        }
        Quiz quiz = new Quiz(questionList);
        quizzes.add(quiz);
    }

    public void printAllUsers(){
        for (User user : userSet){
            System.out.println(user);
        }
    }

    public void printCheapestCourses(){
        for (Course course : courseSet){
            System.out.println(course);
        }
    }

    public void printQuizzes(){
        for (Quiz quiz : quizzes)
            System.out.println(quiz);
    }

    public void setQuizProgramming(int courseId, int quizId){
        Course course = null;
        Quiz quiz = null;

    }

    public void setQuizMath(int courseId, int quizId){
        Course course = null;
        Quiz quiz = null;


    }


}
