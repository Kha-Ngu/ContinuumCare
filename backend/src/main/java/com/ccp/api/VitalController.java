import com.ccp.core.RiskService;
import com.ccp.core.dto.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class VitalController {
  private final RiskService risk;
  private final MongoDatabase db;

  public VitalController(RiskService risk, MongoDatabase db) {
    this.risk = risk;
    this.db = db;
  }

  @PostMapping("/vitals")
  public ResponseEntity<RiskResponse> addVitals(@RequestBody VitalDTO dto) {
    var riskResp = risk.score(dto);

    // store raw vitals
    if (db != null) {
      db.getCollection("vitals").insertOne(new Document()
        .append("patientId", dto.patientId())
        .append("heartRate", dto.heartRate())
        .append("sleepHours", dto.sleepHours())
        .append("weightChange", dto.weightChange())
        .append("ts", dto.tsMillis()));
      // store alert (if any)
      if (riskResp.score() > 0) {
        db.getCollection("alerts").insertOne(new Document()
          .append("patientId", dto.patientId())
          .append("score", riskResp.score())
          .append("reasons", riskResp.reasons())
          .append("createdAt", Instant.now().toString()));
      }
    }
    return ResponseEntity.ok(riskResp);
  }
}
