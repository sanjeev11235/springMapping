package com.san.hibernate.demo;

import java.text.ParseException;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.san.hibernate.demo.entity.Course;
import com.san.hibernate.demo.entity.Instructor;
import com.san.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) throws ParseException {
		
		// create session factory 
		SessionFactory factory = new Configuration()
												.configure("hibernate.cfg.xml")
												.addAnnotatedClass(Instructor.class)
												.addAnnotatedClass(InstructorDetail.class)
												.addAnnotatedClass(Course.class)
												.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId =1;
			Instructor tempInstructor =  session.get(Instructor.class, theId);
			
			// create some courses
			Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse2 = new Course("The Pinball MasterClass");
			
			// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			// save the course
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! ");
		}
		finally {
			session.close();
			factory.close();
			
		}

	}

}
