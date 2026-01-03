package it.cookie.utils.network.managers;

public class NetworkManager {
    String ip_addr;  
    String port;
    public NetworkManager istance;

    private NetworkManager() {}

    public NetworkManager GetIstance() {
        if(istance == null) {
            istance = new NetworkManager();
        }
        return istance;
    }
    
    public void ConnectToServer(String ip, String port) {}

    public void Save() {}

    public String GetBaseURL() {
        return "http://" + ip_addr + ":" + port;
    }
}
