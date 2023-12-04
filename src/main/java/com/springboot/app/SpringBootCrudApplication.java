package com.springboot.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCrudApplication {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(SpringBootCrudApplication.class, args);
		StudentRepo bean = run.getBean(StudentRepo.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go1 = true;
		while (go1) {
			System.out.println("========================================================");
			System.out.println("Welcome To SpringBoot Student Crud Application....");
			System.out.println("========================================================");
			System.out.println("Enter 1 to Add New Student Data");
			System.out.println("Enter 2 to Display All Student Data");
			System.out.println("Enter 3 to Delete Single Student Data");
			System.out.println("Enter 4 to Delete All Student Data");
			System.out.println("Enter 5 to Update  Student Data");
			System.out.println("Enter 6 for Exits.");

			try {
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					System.out.println("Enter Student Name:");
					String name = br.readLine();
					System.out.println("Enter City Name:");
					String city = br.readLine();
					Student s1 = new Student();
					s1.setName(name);
					s1.setCity(city);
					Student save = bean.save(s1);
					System.out.println(save.getName() + " Student Added Successfully.....");
					System.out.println(save);
					break;
				case 2:
					Iterable<Student> findAll = bean.findAll();
					for (Student s : findAll) {
						System.out.println("-----------------------------------------");
						System.out.println("           Student Id:" + s.getId());
						System.out.println("-----------------------------------------");
						System.out.println("Id: " + s.getId());
						System.out.println("Name: " + s.getName());
						System.out.println("City: " + s.getCity());

					}
					break;
				case 3:
					System.out.println("Enter the Student Id for delete:");
					int id = Integer.parseInt(br.readLine());

					bean.deleteById(id);
					System.out.println("Student Deleted Succesfully....");
					break;
				case 4:
					Iterable<Student> findAll2 = bean.findAll();
					bean.deleteAll(findAll2);
					System.out.println("All Data Deleted...");
					break;
				case 5:
					System.out.println("Enter Student Id for update:");
					int uid = Integer.parseInt(br.readLine());
					Optional<Student> findById = bean.findById(uid);
					System.out.println("Enter new Student Name:");
					String uname = br.readLine();
					System.out.println("Enter new Student City:");
					String ucity = br.readLine();
					Student student = findById.get();
					student.setName(uname);
					student.setCity(ucity);

					Student save2 = bean.save(student);
					System.out.println(save2 + " New Student Data has been Saved...");
					break;
				case 6:
					System.out.println("Thankyou for using our Application...");
					go1 = false;
					break;

				default:
					System.out.println("Invalid Choice...");
				}

			} catch (Exception e) {
				System.out.println("Invailed Input...");
				e.printStackTrace();
			}

		}
	}

}
