package com.amazonaws.metrics;

import com.amazonaws.util.AWSRequestMetrics;

public interface MetricsReporter {
	void report(AWSRequestMetrics metrics);
}
