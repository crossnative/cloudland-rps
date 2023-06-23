# 1. RPS Builder
FROM golang as builder
WORKDIR /app
ADD . /app
RUN CGO_ENABLED=0 go build -ldflags="-w -s" -o build/rps .

# 2. RPS Container
FROM gcr.io/distroless/static
COPY --from=builder /app/build/rps /usr/bin/
EXPOSE 8080
ENTRYPOINT ["/usr/bin/rps"]
