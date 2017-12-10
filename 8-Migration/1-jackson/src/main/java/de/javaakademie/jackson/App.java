package de.javaakademie.jackson;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.javaakademie.jackson.model.Staff;

/**
 * App.
 * 
 * @author Guido.Oelmann
 */
public class App {

	public static void main(String[] args) {
		App obj = new App();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();
		Staff staff = createDummyObject();

		try {
			// Normal View -> Salary will be hidden
			System.out.println("Normal View");
			String normalView = mapper.writerWithView(Views.Normal.class).writeValueAsString(staff);
			System.out.println(normalView);

			String jsonInString = "{\"name\":\"Max\",\"age\":33,\"position\":\"Developer\",\"salary\":7000,\"skills\":[\"Java\",\"JavaScript\"]}";
			Staff normalStaff = mapper.readerWithView(Views.Normal.class).forType(Staff.class).readValue(jsonInString);
			System.out.println(normalStaff);

			// Manager View -> Display everything
			System.out.println("\nManager View");
			String managerView = mapper.writerWithView(Views.Manager.class).writeValueAsString(staff);
			System.out.println(managerView);

			Staff managerStaff = mapper.readerWithView(Views.Manager.class).forType(Staff.class)
					.readValue(jsonInString);
			System.out.println(managerStaff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Staff createDummyObject() {
		Staff staff = new Staff();

		staff.setName("Max");
		staff.setAge(33);
		staff.setPosition("Developer");
		staff.setSalary(new BigDecimal("7000"));

		List<String> skills = new ArrayList<>();
		skills.add("Java");
		skills.add("JavaScript");

		staff.setSkills(skills);

		return staff;
	}

}