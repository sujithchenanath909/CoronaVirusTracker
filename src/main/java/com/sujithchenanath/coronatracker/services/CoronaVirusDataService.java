package com.sujithchenanath.coronatracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sujithchenanath.coronatracker.models.LocationStatus;

@Service
public class CoronaVirusDataService {
	
	private String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	public List<LocationStatus> getAllstats() {
		return allstats;
	}

	List<LocationStatus> allstats=new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException {
		List<LocationStatus> newStats=new ArrayList<>();

		HttpClient client =HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(VIRUS_URL)).build();
		HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		
		StringReader reader=new StringReader(response.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records) {
			LocationStatus locationstatus=new LocationStatus();
			locationstatus.setState(record.get("Province/State"));
			locationstatus.setCountry(record.get("Country/Region"));
			int latestCases=Integer.parseInt(record.get(record.size() -1));
			int previousDayCases=Integer.parseInt(record.get(record.size() -2));
			locationstatus.setLatestCases(latestCases);
			locationstatus.setPreviousDayCases(latestCases-previousDayCases);
		    newStats.add(locationstatus);
		}
		    
	this.allstats=newStats;
	}

}
