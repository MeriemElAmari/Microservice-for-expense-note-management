package com.novelis.novy;

import com.novelis.novy.Repository.AgencyRepository;
import com.novelis.novy.Repository.CollaboratorRepository;
import com.novelis.novy.Repository.DepartmentRepository;
import com.novelis.novy.Repository.JobRepository;
import com.novelis.novy.auth.AuthenticationService;
import com.novelis.novy.auth.RegisterRequest;
import com.novelis.novy.model.Agency;
import com.novelis.novy.model.Collaborator;
import com.novelis.novy.model.Department;
import com.novelis.novy.model.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.novelis.novy.enums.Role.ADMIN;
import static com.novelis.novy.enums.Role.USER;


@SpringBootApplication
public class NovyFraisDeNoteApplication {

 private final CollaboratorRepository collaboratorRepository;
 private final JobRepository jobRepository;
 private final DepartmentRepository departmentRepository;
 private final AgencyRepository agencyRepository;

	public NovyFraisDeNoteApplication(CollaboratorRepository collaboratorRepository, JobRepository jobRepository, DepartmentRepository departmentRepository, AgencyRepository agencyRepository) {
		this.collaboratorRepository = collaboratorRepository;
		this.jobRepository = jobRepository;
		this.departmentRepository = departmentRepository;
		this.agencyRepository = agencyRepository;
	}

	@Bean
	public CommandLineRunner commandLineRunner (AuthenticationService service)
	{
		return args -> {
			var agency = Agency.builder()
					.agencyName("ABC Agency")
					.address("123 Main Street")
					.city("New York")
					.postalCode("10001")
					.build();

			agencyRepository.save(agency);
			var department1 = Department.builder()
					.departmentName("HR Department")
					.description("Human Resources")
					.build();
			departmentRepository.save(department1);

			var job1 = Job.builder()
					.jobName("Software Engineer")
					.description("Develops software applications")
					.build();
			jobRepository.save(job1);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateOfBirth1 = dateFormat.parse("1990-01-15");
			Date startDate1 = dateFormat.parse("2022-03-10");
			Date createdAt1 = new Date();
			Date updatedAt1 = new Date();

			Date dateOfBirth2 = dateFormat.parse("1985-05-20");
			Date startDate2 = dateFormat.parse("2021-11-05");
			Date createdAt2 = new Date();
			Date updatedAt2 = new Date();

			// Create sample Collaborator objects
			Collaborator collaborator1 = Collaborator.builder()
					.code("C001")
					.firstname("John")
					.lastname("Doe")
					.title("Software Developer")
					.dateOfBirth(dateOfBirth1)
					.phoneNumber("555-123-4567")
					.email("admin@gmail.com")
					.startDate(startDate1)
					.createdAt(createdAt1)
					.updatedAt(updatedAt1)
					.image("john_doe.jpg")
					.agency(agency)
					.job(job1)
					.department(department1)
					.build();
			Collaborator collaborator = Collaborator.builder()
					.code("C001")
					.firstname("John")
					.lastname("Doe")
					.title("Software Developer")
					.dateOfBirth(dateOfBirth1)
					.phoneNumber("555-123-4567")
					.email("user@gmail.com")
					.startDate(startDate1)
					.createdAt(createdAt1)
					.updatedAt(updatedAt1)
					.image("john_doe.jpg")
					.agency(agency)
					.job(job1)
					.department(department1)
					.build();


			collaboratorRepository.save(collaborator1);
			collaboratorRepository.save(collaborator);

			var admin = RegisterRequest.builder().userName("admin@gmail.com")
					.password("password")
					.role(ADMIN)
					.build();
			var user = RegisterRequest.builder().userName("user@gmail.com")
					.password("password1")
					.role(USER)
					.build();
			System.out.println("admin token : "+service.register(admin).getToken());
			System.out.println("user token : "+service.register(user).getToken());


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(NovyFraisDeNoteApplication.class, args);
	}
}
