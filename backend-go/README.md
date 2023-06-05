# Go Backend for Rock, Paper, Scissors

## 12 Factor App

### 2. Dependencies

### 3. Configuration 

Use environment variables for configuration.

#### Solutions

* https://github.com/kelseyhightower/envconfig

### 4. Backing Services

Treat backing services as attached resources

### 5. Build, release, run

### 6. Processes

### 7. Port Binding

Export services via port binding

#### Solutions
* Exposed HTTP service configurable via `PORT` environment variable.

### 9. Disposablity
Maximize robustness with fast startup and graceful shutdown.

#### Solutions
* Health check for app with https://github.com/hellofresh/health-go.

### 10. Dev/prod parity
Keep development, staging, and production as similar as possible

### 11. Logs
Treat logs as event streams.

#### Solutions
* Syslog

```go
import "log/syslog"

logwriter, err := syslog.Dial("udp", "logsN.papertrailapp.com:XXXXX", LOG_EMERG | LOG_KERN, "myapp")
if err != nil {
    log.Fatal("failed to dial syslog")
}

log.SetOutput(logwriter)

w.Info("Any log message")
w.Err("Another log message")
```
### 12. Admin processes
Run admin/management tasks as one-off processes


# Links and Docs

* https://12factor.net/