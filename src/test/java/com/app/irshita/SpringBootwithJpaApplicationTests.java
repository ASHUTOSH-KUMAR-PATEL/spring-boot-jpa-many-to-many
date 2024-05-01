package com.app.irshita;

import com.app.irshita.model.Role;
import com.app.irshita.model.User;
import com.app.irshita.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class SpringBootwithJpaApplicationTests {

	@Autowired
	private RoleRepository roleRepository;

	// Save Role will also save associated users (Cascade. PERSIST):
	@Test
	void saveRole(){
		User user1 = new User();
		user1.setFirstName("Ashu");
		user1.setLastName("Patel");
		user1.setuEmail("ashu@gmail.com");
		user1.setPassword("secrets1");

		User user2 = new User();
		user2.setFirstName("Irshu");
		user2.setLastName("Patel");
		user2.setuEmail("irshu@gmail.com");
		user2.setPassword("secrets2");

		Role roleAdmin = new Role();
		roleAdmin.setRoleName("ROLE_ADMIN");

		roleAdmin.getUserSet().add(user1);
		roleAdmin.getUserSet().add(user2);

		user1.getRoles().add(roleAdmin);
		user2.getRoles().add(roleAdmin);

		roleRepository.save(roleAdmin);
	}

	// Fetch Role will also fetch its associated user (Fetch type EAGER):
	@Test
	void fetchRole(){
		List<Role> roleList = roleRepository.findAll();
		roleList.forEach((r) -> {
			System.out.println(r.getRoleName());
			r.getUserSet().forEach((u) -> {
				System.out.println(u.getFirstName());
			});
		});
	}
}
