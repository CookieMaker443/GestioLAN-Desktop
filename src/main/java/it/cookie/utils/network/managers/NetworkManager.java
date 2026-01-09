package it.cookie.utils.network.managers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class NetworkManager {
    String ip_addr;  
    int port;
    int timeout = 2000; // in millisecondi

    public static NetworkManager istance;
    private Properties props = new Properties();
    private final String CONFIG_PATH = "config/server.properties";

    private NetworkManager() {
        loadConfig();
    }

    public static synchronized NetworkManager GetIstance() {
        if(istance == null) {
            istance = new NetworkManager();
        }
        return istance;
    }
    
    public void ConnectToServer(String ip, String port) {}

    public void saveConfig(String newIp, int newPort) {
        this.ip_addr = newIp;
        this.port = newPort;
        
        // Per salvare su file in modo persistente fuori dal JAR, 
        // di solito si usa un percorso nel filesystem (es. cartella utente)
        try (OutputStream output = new FileOutputStream("server.properties")) {
            props.setProperty("server.ip", newIp);
            props.setProperty("server.port", String.valueOf(newPort));
            props.store(output, "Server Configuration");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    // Carica i dati dal file
    public void loadConfig() {
        // getResourceAsStream - il file è dentro il JAR/Risorse
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (input == null) {
                System.out.println("Configurazione non trovata, uso valori di default");
                this.ip_addr = "localhost";
                this.port = 8080;
                return;
            }
            props.load(input);
            this.ip_addr = props.getProperty("server.ip");
            this.port = Integer.parseInt(props.getProperty("server.port"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String GetBaseURL() {
        return "http://" + ip_addr + ":" + port;
    }

    public String getIP() { return ip_addr; }

    public int getPort() { return port; }

    public int getTimeout() { return timeout; }

    public void setTimeout(int timeout) { this.timeout = timeout; }
}
