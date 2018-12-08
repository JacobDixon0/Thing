package com.royalslothking.terminalthing;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

import static com.royalslothking.terminalthing.Main.FILE_NOT_FOUND;
import static com.royalslothking.terminalthing.Main.main;

public class Commands {

    public static void drawSquare(){
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     |");
        System.out.println("|_____|");

        // return to main
        main(null);

    }

    public static void list(){
        int longestFileName = 0;

        System.out.println();
        System.out.println(Main.homeDIR);
        System.out.println();
        System.out.println("<DIR>  .");
        System.out.println("<DIR>  ..");

        for(File file : Main.files){
            for(File file2 : Main.files){
                if(file2.name.length() > longestFileName){
                    longestFileName = file2.name.length();
                }
            }
            System.out.print("<" + file.type + ">" + "  " + file.name);
            for( int i = 0; i < ((longestFileName - file.name.length()) + 2); i++) {
                System.out.print(" ");
            }
            System.out.print(file.size + "\n");

        }

        System.out.println();

        // return to main
        main(null);

    }

    public static void pwd(String cwd){
        System.out.println(cwd);
    }

    public static void executeProgram(String fileName){

        if(fileName.equals("timer.exe")){
            timer(10);

        } else if(fileName.equals("progressBar.exe")){
            progressBar(10);

        } else if(fileName.equals("drawSquare.exe")){
            drawSquare();

        } else{
            System.out.println("File is not executable.");
        }

        // return to main
        main(null);
    }

    public static void getTime(String arg){

        if(arg.equals("-u")){
            System.out.println("TIME: " + Instant.now().getEpochSecond());

            // return to main
            main(null);
        }

        Date date  = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd/MM/yyyy 'at' hh:mm:ss a zzz");
        System.out.println(dateFormat.format(date));

        // return to main
        main(null);
    }

    public static void clear(){
        System.out.print("\f");

        // return to main
        main(null);
    }

    public static void touch(String fileSelection){

        if(Main.findFile(fileSelection) != FILE_NOT_FOUND){
            System.out.println("File with the same name already exists.");
        }else{
            Main.files.add(new File(fileSelection, "NFT"));
        }

        // return to main
        main(null);

    }

    public static void write(File file){

        if(file != FILE_NOT_FOUND && !file.type.equals("EXE")){

            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();

            file.data = s;

        } else if(file.type.equals("EXE")){
            System.out.println("File is not editable.");

        } else if(file == FILE_NOT_FOUND){
            System.out.println("File not found.");

        } else{
            System.out.println("Read error.");
        }

        // return to main
        main(null);
    }

    public static void cat(File file){

        if(file != FILE_NOT_FOUND && !file.type.equals("EXE") && file.data != null){
            System.out.println(file.data);
        }else if(file.type.equals("EXE")){
            System.out.println("File is not readable.");
        }else if(file == FILE_NOT_FOUND){
            System.out.println("File not found.");
        }else{
            System.out.println("Read error.");
        }

        // return to main
        main(null);

    }

    public static void timer(long duration){

        long now = Instant.now().getEpochSecond();
        long startTime = now;
        long lastTime = now;
        long endTime = startTime + duration;
        int counter = 0;

        while(endTime > now){
            now = Instant.now().getEpochSecond();
            if(lastTime != now){
                if(now == endTime){
                    System.out.println(counter);
                }else {
                    System.out.println(counter);
                }
                counter++;
                lastTime = now;
            }
        }

        System.out.println();

        // return to main
        main(null);

    }

    public static void progressBar(long duration){

        long now = Instant.now().getEpochSecond();
        long endTime = now + duration;
        long lastTime = now;
        int counter = 1;

        System.out.print("[");
        for(int i =0; i < duration; i++){
            System.out.print(".");
        }
        System.out.print("]\n");

        while(endTime > now){
            now = Instant.now().getEpochSecond();

            if(lastTime != now){
                System.out.print("\f");
                System.out.print("[");
                for(int i = 0; i < counter; i++){
                    System.out.print("#");
                }
                for(int i = 0; i < (duration - counter); i++){
                    System.out.print(".");
                }
                System.out.print("]\n");

                lastTime = now;
                counter++;
            }

        }

        // return to main
        System.out.println();
        main(null);

    }

    public static void help(){

        System.out.println();
        System.out.println("ls    - Lists files in current directory.");
        System.out.println("cd    - Changes the current directory.");
        System.out.println("mv    - Moves file between directories and/or renames file.");
        System.out.println("clear - Clears the console.");
        System.out.println("cat   - Prints the contents of a file to the screen.");
        System.out.println("write   - Edits the contents of a file.");
        System.out.println("touch - Creates a new file.");
        System.out.println("time  - Prints the current time to the screen.");
        System.out.println(" | -u Prints current UNIX timestamp.");
        System.out.println("exit  - Exits the application.");
        System.out.println();
        System.out.println("Executable programs can be run with \'./filename\'.");
        System.out.println();

        // return to main
        main(null);
    }

    public static void noAccess(){
        System.out.println("Access denied.");

        // return to main
        main(null);
    }

}
