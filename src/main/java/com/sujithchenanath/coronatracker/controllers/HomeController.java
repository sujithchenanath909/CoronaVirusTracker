package com.sujithchenanath.coronatracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sujithchenanath.coronatracker.models.LocationStatus;
import com.sujithchenanath.coronatracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusData;
	
	@GetMapping("/")
	public String home(Model model) {	
		
		List<LocationStatus> allStats=coronaVirusData.getAllstats();
		int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatestCases()).sum();
		int totalDiffCases=allStats.stream().mapToInt(stat->stat.getPreviousDayCases()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalDiffCases",totalDiffCases);
		return "home";
	}
	
}
