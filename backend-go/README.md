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

Exposed HTTP service configuratable via `PORT` environment variable.

# Links and Docs

* https://12factor.net/