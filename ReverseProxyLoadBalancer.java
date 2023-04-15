import java.io.*;
import java.net.*;
import java.util.*;

public class ReverseProxyLoadBalancer {
    private int proxyPort;
    private List<InetAddress> backendServers;
    private int backendPort;
    private int currentIndex;

    public ReverseProxyLoadBalancer(int proxyPort, List<InetAddress> backendServers, int backendPort) {
        this.proxyPort = proxyPort;
        this.backendServers = backendServers;
        this.backendPort = backendPort;
        this.currentIndex = 0;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(proxyPort);
        while (true) {
            Socket clientSocket = serverSocket.accept();

            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String request = br.readLine();

            InetAddress backendServer = backendServers.get(currentIndex);
            currentIndex = (currentIndex + 1) % backendServers.size();

            Socket backendSocket = new Socket(backendServer, backendPort);
            OutputStreamWriter osw = new OutputStreamWriter(backendSocket.getOutputStream());
            osw.write(request);
            osw.flush();

            InputStreamReader isr2 = new InputStreamReader(backendSocket.getInputStream());
            BufferedReader br2 = new BufferedReader(isr2);
            String response = br2.readLine();

            OutputStreamWriter osw2 = new OutputStreamWriter(clientSocket.getOutputStream());
            osw2.write(response);
            osw2.flush();

            clientSocket.close();
            backendSocket.close();
        }
    }
}

