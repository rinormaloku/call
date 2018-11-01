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

