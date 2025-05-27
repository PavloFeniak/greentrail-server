docker build . -t auth-sidecar:local
kind load docker-image auth-sidecar:local --name greentrail-cluster