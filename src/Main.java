public class Main {
    public static void main(String[] args) {
        FileCommanderCLI fc = new FileCommanderCLI(System.in, System.out);
        try{
            fc.eventLoop();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}