Feature: Risk scoring via API
  As a clinician
  I want the API to explain alerts
  So I can trust why a risk was raised

  Scenario: Alert explains high HR + low sleep
    Given a patient "p1" sends vitals with heartRate 110 and sleepHours 5.0
    When I POST these vitals to "/api/v1/vitals"
    Then I receive a 200 response
    And the response includes a "score" >= 2
    And the "reasons" contain "HR>100 && Sleep<6"
