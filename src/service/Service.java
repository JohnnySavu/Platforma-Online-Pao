package service;

import audit.AuditService;
import main.Main;
import models.courses.Course;
import models.courses.Enrollment;
import models.courses.MathCourse;
import models.courses.ProgrammingCourse;
import models.exceptions.NoSuchCourseException;
import models.exceptions.NoSuchTeacherException;
import models.quiz.Question;
import models.quiz.Quiz;
import models.users.Admin;
import models.users.Student;
import models.users.Teacher;
import models.users.User;
import repository.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class Service {
    private TreeSet<Course> courseSet;
    private TreeSet<User> userSet;
    private List<Quiz> quizzes;

    public static ReadingFromFileService readingCSV = ReadingFromFileService.getInstance();
    public static WritingInFileService writingCSV = WritingInFileService.getInstance();
    public static AuditService audit = AuditService.getInstance();

    public static Service instance;

    public static StudentService studentService = StudentService.getInstance();
    public static TeacherService teacherService = TeacherService.getInstance();
    public static QuestionService questionService = QuestionService.getInstance();
    public static MathCourseService mathCourseService = MathCourseService.getInstance();
    public static ProgrammingCourseService programmingCourseService = ProgrammingCourseService.getInstance();

    public static Service getInstance(){
        if (instance == null)
            instance = new Service();
        return instance;
    }

    private Service(){
        this.userSet = new TreeSet<>();
        this.courseSet = new TreeSet<>();
        this.quizzes = new ArrayList<>();
    }

    public LocalDate randomBirthday() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return randomBirthDate;

    }

    public float randomScores() {
        Random random = new Random();
        return random.nextFloat() * random.nextInt(10) + 1;

    }
    public float randomSalary() {
        Random random = new Random();
        return random.nextFloat() * random.nextInt(2500) + 1;

    }

    public int randomInt() {
        Random random = new Random();
        return random.nextInt();

    }

    public void addNewStudent(String nume, String phoneNumber, String email){
        Student aux = new Student(nume, phoneNumber, email);
        aux.setBirthday(randomBirthday());
        aux.setMathScore(randomScores());
        aux.setProgrammingScore(randomScores());
        aux.setAdress("None");
        userSet.add(aux);
        if (Main.tip_salvare == 1)
            StudentRepository.save(aux);
        else
            studentService.addStudent(aux);
    }

    public void addNewTeacher(String nume, String phoneNumber, String email){
        Teacher aux = new Teacher(nume, phoneNumber, email);
        aux.setBirthday(randomBirthday());
        aux.setNoYearsExperience(randomInt() % 10);
        aux.setRating(randomScores());
        aux.setSalary(randomSalary());
        userSet.add(aux);
        if (Main.tip_salvare == 1)
            TeacherRepository.save(aux);
        else
            teacherService.addTeacher(aux);
    }

    public void printStudentCourses(int id){
        List<Integer> courses =  Enrollment.getInstance().getCourses(id);
        System.out.println("Courses for this student are : ");
        if (courses != null){
            for (int idCourse :courses){
                Course course = getCourseById(idCourse);
                System.out.println(course);
            }
        }
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
            StudentRepository.delete(student.getId());
            userSet.remove(student);
    }

    public  void deleteTeacher(int id){
        Teacher teacher = getTeacherById(id);
        if (teacher == null)
            System.out.println("No such teacher");
        else
            TeacherRepository.delete(teacher.getId());
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

    public Teacher getTeacherById(int id){
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

        MathCourse newCourse = new MathCourse(name, noHours, price, subject, teacher);
        courseSet.add(newCourse);
        if (Main.tip_salvare == 1)
            MathCourseRepository.save(newCourse);
        else
            mathCourseService.addMathCourse(newCourse);
    }

    public void addNewProgrammingCourse(String name, int noHours, float price, List<String> projectsRequirments,
                                        int noProjects, String programmingLanguage, int teacherId) throws NoSuchTeacherException {

        Teacher teacher = getTeacherById(teacherId);
        if (teacher == null)
            throw new NoSuchTeacherException("No teacher with this id");

        ProgrammingCourse newCourse = new ProgrammingCourse(name, noHours, price, projectsRequirments,
                noProjects, programmingLanguage, teacher);

        courseSet.add(newCourse);
        if (Main.tip_salvare == 1)
            ProgrammingCourseRepository.save(newCourse);
        else
            programmingCourseService.addProgrammingCourse(newCourse);
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
            System.out.println("Introuce the number of answers");
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
                System.out.println("Introduce answer nb " + (i + 1));
                String answer = scanner.nextLine();
                answers.add(answer);
            }
            System.out.println("Introduce the number of correct answers:");

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
                System.out.println("Introduce the " + (i+1) + " correct answer");
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
            questionService.addQuestionService(question,answers,correctAnswers);
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

    private ProgrammingCourse getProgrammingCourseById(int id){
        for (Course course : courseSet)
            if (course instanceof ProgrammingCourse && course.getId() == id)
                return (ProgrammingCourse) course;
        return null;
    }

    private MathCourse getMathCourseById(int id){
        for (Course course : courseSet)
            if (course instanceof MathCourse && course.getId() == id)
                return (MathCourse) course;
        return null;
    }


    public void setQuizProgramming(int courseId, int quizId){
        Course course = getProgrammingCourseById(courseId);
        Quiz quiz = getQuizById(quizId);
        if (course == null || quiz == null){
            System.out.println("Invalid arguments");
            return;
        }
        course.setFinalQuiz(quiz);
    }

    public void setQuizMath(int courseId, int quizId){
        Course course = getMathCourseById(courseId);
        Quiz quiz = getQuizById(quizId);
        if (course == null || quiz == null){
            System.out.println("Invalid arguments");
            return;
        }
        course.setFinalQuiz(quiz);
    }
    private Course getCourseById(int id){
        for (Course course : courseSet)
            if (course.getId() == id)
                return course;

        return null;
    }

    public void enrollStudent(int studentId, int courseId){
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        Enrollment instance = Enrollment.getInstance();

        instance.addToCourse(student.getId(), course.getId());
        if (Main.tip_salvare == 1){
            EnrollmentRepository.add(student.getId(), course.getId());
        }
    }

    public void disenrollStudent(int studentId, int courseId){
        Student student = getStudentById(studentId);
        Course course = getCourseById(courseId);
        Enrollment instance = Enrollment.getInstance();
        try {
            instance.removeFromCourse(student.getId(), course.getId());
            if (Main.tip_salvare == 1){
                EnrollmentRepository.delete(student.getId(), course.getId());
            }
        }catch(NoSuchCourseException e){
            System.out.println(e.getMessage());
        }

    }

    public void printAllCourses(){
        for (var course : courseSet){
            System.out.println(course.toString());
        }
    }

    public void loadData() {
        try {
            if (Main.tip_salvare == 1){
                userSet.addAll(StudentRepository.load());
                userSet.addAll(TeacherRepository.load());
                courseSet.addAll(MathCourseRepository.load());
                courseSet.addAll(ProgrammingCourseRepository.load());
                EnrollmentRepository.load();
            }
            else{
                studentService.readStudent();
                userSet.addAll(StudentService.studentList);

                teacherService.readTeacher();
                userSet.addAll(TeacherService.teacherList);

                questionService.readQuestionService();

                mathCourseService.readMathCourse();
                courseSet.addAll(MathCourseService.MathCourseList);

                programmingCourseService.readProgrammingCourse();
                courseSet.addAll(ProgrammingCourseService.ProgrammingCourseList);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
