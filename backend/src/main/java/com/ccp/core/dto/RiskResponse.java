package main.java.com.ccp.core.dto;

import java.util.List;

public record RiskResponse(int score, List<String> reasons) {}
