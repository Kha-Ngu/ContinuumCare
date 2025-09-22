Feature: Clinician dashboard
  Scenario: See a high-risk patient with reasons
    Given the app is open
    When I view the patient list
    Then I see a "High" risk badge
    And I can expand to view "HR>100 && Sleep<6"
