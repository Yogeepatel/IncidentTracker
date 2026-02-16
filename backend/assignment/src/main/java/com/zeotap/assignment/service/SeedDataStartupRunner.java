package com.zeotap.assignment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedDataStartupRunner {

	private final SeedDataService seedDataService;
	private final boolean seedEnabled;

	public SeedDataStartupRunner(
			SeedDataService seedDataService,
			@Value("${app.seed.enabled:true}") boolean seedEnabled) {
		this.seedDataService = seedDataService;
		this.seedEnabled = seedEnabled;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		if (seedEnabled) {
			seedDataService.addRandomDataToTable();
		}
	}
}
