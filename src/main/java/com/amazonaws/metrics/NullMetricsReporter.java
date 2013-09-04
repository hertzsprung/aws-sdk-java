package com.amazonaws.metrics;

import com.amazonaws.util.AWSRequestMetrics;

public class NullMetricsReporter implements MetricsReporter {
	public void report(AWSRequestMetrics metrics) { }
}
