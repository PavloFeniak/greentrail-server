
SERVICE_NAME=$1
IMAGE_TAG=$2

cp config/Dockerfile $SERVICE_NAME/Dockerfile
cd $SERVICE_NAME || exit 1

./gradlew clean build

docker build . -t $SERVICE_NAME:$IMAGE_TAG
kind load docker-image $SERVICE_NAME:$IMAGE_TAG --name greentrail-cluster

kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

kubectl get pods -w

rm Dockerfile