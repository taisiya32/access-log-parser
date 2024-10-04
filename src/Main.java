import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        findfile();
    }

    public static void findfile() {
        int correct_ways_count = 1;
        while(true) {
            System.out.println("Укажите путь к файлу");
            String path = new Scanner(System.in).nextLine();
            //C:\Users\prost\Desktop\FileDirectory\file.txt
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExists==false) {System.out.println("Файл не существует"); continue;}
            if (isDirectory==true) {System.out.println(" Указанный путь является путём к папке, а не к файлу"); continue;}
            if ((fileExists==true)&&(isDirectory==false)) {
                System.out.println("Путь указан верно. " + "Это файл номер " + correct_ways_count);
                correct_ways_count++;
            }
        }}

}

