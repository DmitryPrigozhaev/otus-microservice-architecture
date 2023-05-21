# Lesson 7

Prometheus. Grafana.

### Task

Instrument the service from the last lesson with metrics in the Prometheus format
using the library for your framework and program language. Make a dashboard in
`Grafana`, which would have metrics broken down by API methods:

1. Latency (response time) with quantiles of 0.5, 0.95, 0.99, max;
2. RPS;
3. Error Rate (number of 500 responses).

Add graphs to the dashboard with metrics for the whole service, taken from `nginx-ingress-controller`:

1. Latency (response time) with quantiles of 0.5, 0.95, 0.99, max;
2. RPS;
3. Error Rate (number of 500 responses).

Set up an alert in the `Grafana` for Error Rate and Latency.

### Provide output:

1. Screenshots of dashboards with charts at the time of service stress testing. For example,
   after 5-10 minutes of exercise;
2. JSON dashboards.

### Task with a star (+5 points)

Using existing system metrics from `Kubernetes`, add charts with metrics to the dashboard:

1. Application pods memory consumption;
2. Application pods CPU consumption.

Instrument the database with the prometheus exporter for this database.
Add graphs with database operation metrics to the general dashboard.
