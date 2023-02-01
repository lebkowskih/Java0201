import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        zadaniePierwsze();
        zadanieDrugie();
        zadanieTrzecie();
        zadanieCzwarte("www.wp.pl");
        zadaniePiate("www.google.com");
    }

    public static void zadaniePierwsze() throws IOException {
        URL ug = new URL("https://ug.edu.pl/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ug.openStream())
        );
        System.out.println(linkiZeStrony(in));
    }
    public static ArrayList<String> linkiZeStrony(BufferedReader in) throws IOException {
        ArrayList<String> linki = new ArrayList<String>();

        String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        String line;
        Pattern p = Pattern.compile(regex);
        try {
        while((line = in.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    linki.add(m.group());
                }
            }
        } finally {
            return linki;
        }
    }
    public static void zadanieDrugie() throws IOException {
        try {
            String[] linki = {"www.ug.edu.pl", "www.onet.pl", "www.interia.pl"};
            for (String link: linki) {
                InetAddress address = InetAddress.getByName(link);
                System.out.println("IP address: " + link + " to " + address.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void zadanieTrzecie() {
        InetAddress inetAddress = null;
        try{
            inetAddress = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e){
            System.out.println("Nie można uzyskać adresu IP dla tego komputera.");
            System.exit(0);
        }
        String ip = inetAddress.getHostAddress();
        String host = inetAddress.getHostName();
        System.out.println("Adres IP tego komputera to: " + ip + " a nazwa: " + host);
    }

    public static void zadanieCzwarte(String passedHost) throws UnknownHostException {
        InetAddress inetAddress = null;
        inetAddress = InetAddress.getByName(passedHost);

        String ip = inetAddress.getHostAddress();
        String host = inetAddress.getHostName();
        System.out.println("Adres IP tego komputera to: " + ip + " a nazwa: " + host);
    }

    public static void zadaniePiate(String host) {
        for (int port = 1; port <= 65535; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 1000);
                System.out.println("Port " + port + " jest otwarty");
                socket.close();
            } catch (IOException ex) {
                System.out.println("Port jest zamknięty");
            }
        }
    }
}