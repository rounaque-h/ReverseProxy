# ReverseProxy

<h3 align="left">This code provides a simple implementation of a reverse proxy and load balancer using Java. The code uses a round-robin algorithm for load balancing, which distributes incoming requests equally among the available backend servers.</h3>


<h2 align="left">Contributing:</h2>
  
<h3 align="left">Contributions to this code are welcome! If you find a bug or would like to suggest an improvement, please submit an issue or pull request.</h3>


<h2 align="left">Getting Started:</h2>

<h3 align="left">To use the reverse proxy and load balancer, you will need to create an instance of the ReverseProxyLoadBalancer class and specify the following parameters:
proxyPort: The port number on which the proxy server will listen for incoming client requests.
backendServers: A list of InetAddress objects representing the addresses of the available backend servers.
backendPort: The port number on which the backend servers are listening for incoming requests.
You can then call the start() method on the ReverseProxyLoadBalancer instance to start the server.</h3>
<h3 align="left">This will start the reverse proxy and load balancer on port 8080 and forward incoming requests to either backendServer1 or backendServer2 using round-robin algorithm on port 80.</h3>
  
```java
InetAddress backendServer1 = InetAddress.getByName("backendServer1");
InetAddress backendServer2 = InetAddress.getByName("backendServer2");
List<InetAddress> backendServers = Arrays.asList(backendServer1, backendServer2);

ReverseProxyLoadBalancer loadBalancer = new ReverseProxyLoadBalancer(8080, backendServers, 80);
loadBalancer.start();
