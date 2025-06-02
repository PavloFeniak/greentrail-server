docker build . -t logging-sidecar:local
kind load docker-image logging-sidecar:local --name greentrail-cluster