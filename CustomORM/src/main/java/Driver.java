import com.homeschool.model.*;

public class Driver {
    public static void main(String[] args) {


        for (Subject eachSubject : Subject.values()) {
            System.out.println(eachSubject);
        }

        for(int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();

        for (Grade eachGrade: Grade.values()) {
            System.out.println(eachGrade);
        }

        Course newCourse = new Course("Linear Algebra", "Math.234", Subject.MATHEMATICS);
        System.out.println(newCourse.toString());

        Student newStudent = new Student();
        System.out.println(newStudent.toString());

        Instructor newInstructor = new Instructor();
        System.out.println(newInstructor.toString());

    }
}
