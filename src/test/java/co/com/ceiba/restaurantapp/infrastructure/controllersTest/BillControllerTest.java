package co.com.ceiba.restaurantapp.infrastructure.controllersTest;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import co.com.ceiba.restaurantapp.infrastructure.controllers.BillController;


@RunWith(SpringRunner.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BillController billController;
	
		
	@Test
	public void getBillById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/bill/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}
}
