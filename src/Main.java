import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main (String[] args)  {
        findfile();
        String path = new Scanner(System.in).nextLine();
        //C:\Users\prost\Desktop\FileDirectory\file.txt
        //C:\Users\prost\Downloads\access.log
        File file = new File(path);
    }

    public static void findfile() {
        int correct_ways_count = 1;
        while (true) {
            System.out.println("Укажите путь к файлу");
            String path = new Scanner(System.in).nextLine();
            //C:\Users\prost\Desktop\FileDirectory\file.txt
            //C:\Users\prost\Downloads\access.log
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            int countLines = 0;
            int yaBot = 0;
            int googleBot = 0;

            if (!fileExists) {
                System.out.println("Файл не существует");
                continue;
            }
            if (isDirectory) {
                System.out.println(" Указанный путь является путём к папке, а не к файлу");
                continue;
            }
            if ((fileExists) && (!isDirectory)) {
                System.out.println("Путь указан верно. " + "Это файл номер " + correct_ways_count);
                correct_ways_count++;

                ArrayList<Integer> ln = new ArrayList();

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;

                    while ((line = reader.readLine()) != null) {
                        countLines++;
                        int length = line.length();
                        ln.add(countLines - 1, length);

                        String[] parts = line.split("compatible;");

                        if (parts.length >= 2) {
                            String fragment = parts[1];
                     //      System.out.println(fragment);
                            String[] partsWithUserAgent = fragment.split("/");
                            if (partsWithUserAgent.length >= 2){
                                String agent = partsWithUserAgent[0].trim();
                     //          System.out.println(agent);
                                 if (agent.equals("YandexBot")) {
                                    yaBot++;
                                }
                                if (agent.equals("Googlebot")) {
                                    googleBot++;
                                }
                            }
                        }


                    }


                    System.out.println("Доля запросов от YandexBot = "+ yaBot);
                    System.out.println("Доля запросов от GoogleBot = "+ googleBot);


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("Количество строк в файле: " + countLines);

                int min = ln.getFirst();
                int max = ln.getFirst();

                for (Integer i : ln) {
                    if (i > max)
                        max = i;

                    if (i < min)
                        min = i;}
                try{

                    if(max>1024) {
                        throw new LongLineException("Длина строки не должна превышать 1024 символа");
                    }
                }catch (LongLineException e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}

