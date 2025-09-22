import com.ccp.core.dto.*;
import java.util.*;

public class RiskService {

  public RiskResponse score(VitalDTO v) {
    int score = 0;
    List<String> reasons = new ArrayList<>();

    if (v.heartRate() > 100 && v.sleepHours() < 6.0) {
      score += 2;
      reasons.add("HR>100 && Sleep<6");
    }
    if (v.weightChange() >= 5.0) {
      score += 3;
      reasons.add("WeightGain>=5");
    }
    return new RiskResponse(score, reasons);
  }
}
