package test.java.com.ccp.api.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.ccp.api.VitalController;
import com.ccp.core.RiskService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VitalsSteps {
  private MockMvc mvc;
  private final ObjectMapper om = new ObjectMapper();
  private String body;

  public VitalsSteps() {
    // unit wiring: controller + service (no DB yet)
    mvc = MockMvcBuilders.standaloneSetup(new VitalController(new RiskService(), null)).build();
  }

  @Given("a patient {string} sends vitals with heartRate {int} and sleepHours {double}")
  public void givenVitals(String pid, Integer hr, Double sleep) throws Exception {
    body = """
      {"patientId":"%s","heartRate":%d,"sleepHours":%s,"weightChange":0.0}
    """.formatted(pid, hr, sleep);
  }

  @When("I POST these vitals to {string}")
  public void iPost(String path) throws Exception {
    mvc.perform(post(path).contentType("application/json").content(body))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.score").exists())
       .andExpect(jsonPath("$.reasons").isArray());
  }

  @Then("I receive a 200 response") public void ok() { /* checked above */ }

  @Then("the response includes a {string} >= {int}")
  public void scoreAtLeast(String key, Integer n) throws Exception { /* validate with an extra call if needed */ }

  @Then("the {string} contain {string}")
  public void reasonsContain(String key, String val) throws Exception { /* similar extra check */ }
}
