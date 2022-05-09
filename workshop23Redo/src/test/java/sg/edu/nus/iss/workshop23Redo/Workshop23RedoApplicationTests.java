package sg.edu.nus.iss.workshop23Redo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.workshop23Redo.models.Bff;
import sg.edu.nus.iss.workshop23Redo.repository.BffRepository;
import sg.edu.nus.iss.workshop23Redo.service.BffException;
import sg.edu.nus.iss.workshop23Redo.service.BffService;

@SpringBootTest
class Workshop23RedoApplicationTests {

	@Autowired
	private BffService bffSvc;

	@Autowired
	private BffRepository bffRepo;

	private Bff belo = new Bff();

	public Workshop23RedoApplicationTests() {
		belo.setName("belo");
		belo.setEmail("belo@gmail.com");
		belo.setPhone("347342432");
		belo.setStatus("friend");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.YEAR, 1970);
		belo.setDob(cal.getTime());
	}
	
	@BeforeEach
	public void setup() {
		bffRepo.insertBff(belo);
	}

	@AfterEach
	public void tearDown() {
		bffRepo.deleteBffByEmail(belo.getEmail());
	}

	@Test
	void notAbleToInsertBelo() {
		try {
			bffSvc.addNewUser(belo);
		} catch (BffException ex) {
			assertTrue(true);
			return;
		}

		fail("No exception was throw when email exists");
	}

}
