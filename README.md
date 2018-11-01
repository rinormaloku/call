## Explanation of the sources

The Call service has one endpoint `/call` which:
1. Makes a GET request to the HTTPBIN service on the path `/ip`
2. Resulting on the following json:

```
{
  origin: "172.17.0.1"
}
```

URL to the httpbin service is defined using the HTTPBIN_URL environment variable in the deployment for [Call Service](./call-service.yaml)

## Istio Resources:

- [HTTP Gateway](./istio-resources/http-gateway.yaml) to accept calls in port 80.
- [Virtual Service External Call](./istio-resources/virtualservice-external-call.yaml) to bind to HTTP Gateway and forward requests with the path `/call` to the [Call Service](./call-service.yaml)
- [Virtual Service HTTPBIN](./istio-resources/virtualservice-httpbin.yaml) delays all requests for 15 seconds. !! NOT WORKING.







Reproducing the bug:

**Steps to reproduce the bug**
1. Install Istio and enable auto injection.
```
$ helm template install/kubernetes/helm/istio --name istio --set global.mtls.enabled=false --set tracing.enabled=true --set kiali.enabled=true --set grafana.enabled=true --namespace istio-system > istio.yaml

$ kubectl label namespace default istio-injection=enabled
```
2. Create a sample app:
```
$ kubectl apply -f https://raw.githubusercontent.com/rinormaloku/call/master/call-service.yaml
$ kubectl apply -f https://raw.githubusercontent.com/rinormaloku/call/master/httpbin.yaml
```
3. Add an HTTP Gateway and VirtualService to send requests to the **Call Service**
```
$ kubectl apply -f https://raw.githubusercontent.com/rinormaloku/call/master/istio-resources/http-gateway.yaml
$ kubectl apply -f https://github.com/rinormaloku/call/blob/master/istio-resources/virtualservice-external-call.yaml
```

4. (**The issue**) Add VirtualService to delay all calls from the Call service to HTTPBIN
```
$ kubectl apply -f https://github.com/rinormaloku/call/blob/master/istio-resources/virtualservice-httpbin.yaml
```

5. CURL to the Call Service:
`curl $INGRESSGATEWAY_IP/call`

