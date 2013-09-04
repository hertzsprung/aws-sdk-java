package com.amazonaws.metrics;

import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.TimingInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class LoggingMetricsReporter implements MetricsReporter {
    private static final Log latencyLogger = LogFactory.getLog("com.amazonaws.latency");
    private static final Object KEY_VALUE_SEPARATOR = "=";
    private static final Object COMMA_SEPARATOR = ", ";

	public void report(AWSRequestMetrics metrics) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, List<Object>> entry : metrics.getProperties().entrySet()) {
            keyValueFormat(entry.getKey(), entry.getValue(), builder);
        }

        for (Map.Entry<String, Number> entry : metrics.getTimingInfo().getAllCounters().entrySet()) {
            keyValueFormat(entry.getKey(), entry.getValue(), builder);
        }

        for (Map.Entry<String, List<TimingInfo>> entry : metrics.getTimingInfo().getSubMeasurementsByName().entrySet()) {
            keyValueFormat(entry.getKey(), entry.getValue(), builder);
        }

        latencyLogger.info(builder.toString());
	}

    private void keyValueFormat(Object key, Object value, StringBuilder builder) {
        builder.append(key).append(KEY_VALUE_SEPARATOR).append(value).append(COMMA_SEPARATOR);
    }
}
