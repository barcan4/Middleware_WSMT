import Client.Client;

public class mainSystem {
    public static void main(String[] args) {
        try {
            if (args.length > 2) {
                new Client(args[0], args[1], args[2]);
            } else {
                new Client("localhost", "myRequest", "myResponse");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
