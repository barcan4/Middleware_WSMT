public class mainSystem {
    public static void main(String[] args) {
        try {
            if (args.length > 2) {
                new Server(args[0], args[1], args[2]);
            } else {
                new Server("localhost", "myRequest", "myResponse");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
