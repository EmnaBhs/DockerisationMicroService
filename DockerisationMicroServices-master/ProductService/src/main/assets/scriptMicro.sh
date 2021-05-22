kubectl create deployment config-server --image=alibouriga/configserver:0.0.1-SNAPSHOT
kubectl expose deployment config-server --type=LoadBalancer --port 8888

kubectl create deployment discovery-service --image=alibouriga/eureka:0.0.1-SNAPSHOT
kubectl expose deployment discovery-service --type=LoadBalancer --port 8761

