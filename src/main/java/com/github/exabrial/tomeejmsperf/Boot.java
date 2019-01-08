package com.github.exabrial.tomeejmsperf;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@Lock(LockType.READ)
public class Boot {
	@Inject
	private PerfTestRunner runner;

	@PostConstruct
	public void postConstruct() {
		runner.go();
	}
}
