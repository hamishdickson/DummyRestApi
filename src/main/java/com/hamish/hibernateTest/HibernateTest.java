package com.hamish.hibernateTest;

/**
 * Created by hamishdickson on 28/10/14.
 *
 */

import com.hamish.models.Student;
import com.hamish.utils.HibernateUtil;
import org.hibernate.Session;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Student student = new Student();

        student.setStudentName("Mishy");
        student.setStudentAge("31");

        session.save(student);
        session.getTransaction().commit();
        System.out.println("Wow I'm old");
    }
}
