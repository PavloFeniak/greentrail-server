kubectl apply -f prometheus-config.yaml
kubectl apply -f grafana-deployment.yaml
kubectl apply -f prometheus-deployment.yaml
kubectl apply -f prometheus-service.yaml
kubectl apply -f grafana-service.yaml