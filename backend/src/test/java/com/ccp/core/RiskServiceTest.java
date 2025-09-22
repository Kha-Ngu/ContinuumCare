package test.java.com.ccp.core;

import com.ccp.core.dto.VitalDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RiskServiceTest {

  @Test
  void highHeartRateAndLowSleepRaisesRisk() {
    RiskService svc = new RiskService();
    var vital = new VitalDTO("p1", 105, 5.0, 70.0, System.currentTimeMillis());
    var resp = svc.score(vital);
    assertTrue(resp.score() >= 2, "Expected elevated risk score");
    assertTrue(resp.reasons().contains("HR>100 && Sleep<6"), "Reason should explain the alert");
  }

  @Test
  void normalVitalsAreLowRisk() {
    RiskService svc = new RiskService();
    var vital = new VitalDTO("p1", 75, 7.5, 0.0, System.currentTimeMillis());
    var resp = svc.score(vital);
    assertEquals(0, resp.score());
    assertTrue(resp.reasons().isEmpty());
  }
}
