public class Main {
    public static void main(String[] args) {
        FileCommander fc = new FileCommander();
        try{
            for(String s : fc.find("klipper")){
                System.out.println(s);
            }
        }catch (Exception e){

        }

    }
}