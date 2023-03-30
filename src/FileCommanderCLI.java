import java.io.*;

public class FileCommanderCLI {

    BufferedWriter out;
    BufferedReader in;

    private FileCommander fc;

    public FileCommanderCLI(InputStream inputStream, OutputStream outputStream){
        in = new BufferedReader(new InputStreamReader(inputStream));
        out = new BufferedWriter(new OutputStreamWriter(outputStream));
        fc = new FileCommander();
    }

    public void eventLoop() throws IOException{
        while(true){
            runCommand(in.readLine());
        }

    }

    private void runCommand(String line) throws IOException{
        String[] reggae = line.split(" ");
        if(reggae.length > 1 ) {
            switch (reggae[0]) {
                case "ls": out.write(fc.ls(reggae[1]).toString()); out.flush(); break;
                case "pwd": out.write(fc.pwd()); break;
                case "find": out.write(fc.find(reggae[1]).toString()); break;
                case "cd": fc.cd(reggae[1]); break;

            }
        }
    }
}
