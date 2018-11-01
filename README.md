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
