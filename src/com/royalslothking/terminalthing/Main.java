package com.royalslothking.terminalthing;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{

    private static final String USERNAME = "youre";
    private static final String PASSWORD = "mum";

    public static boolean initialized = false;

    public static String defaultUsername = "guest";
    public static String homeDIR = "/home/guest/Desktop/";
    public static String homeDIRs = "~/Desktop";
    public static String systemName = "system-id";

    private static boolean loggedIn = false;

    // Load Startup Files

    public static ArrayList<File> files = new ArrayList<>();

    public static final File FILE_NOT_FOUND = new File("FILE_NOT_FOUND", "NUL", false);

    public static File welcome     = new File("welcome.txt", "TXT", "hi :)", true);
    public static File timer       = new File("timer.exe", "EXE", true);
    public static File progressBar = new File("progressBar.exe", "EXE", true);
    public static File drawSquare  = new File("drawSquare.exe", "EXE", true);

    public static void init(){

        if(!System.getProperty("os.name").equals("Windows 10")){
            System.out.println("Your operating system may not be compatible with this program and could cause issues, continue? (Y/N)");
            System.out.print("$ ");
            Scanner s = new Scanner(System.in);
            String initOSquery = s.nextLine();

            if(initOSquery.toLowerCase().equals("n")) {
                System.exit(0);
            }else if(!initOSquery.toLowerCase().equals("y") && !initOSquery.toLowerCase().equals("n")){
                init();
            }

            System.out.println();
        }

        timer.assignEXE("timer.exe");
        progressBar.assignEXE("progressBar.exe");
        drawSquare.assignEXE("drawSquare.exe");

    }

    public static void main(String[] args) {

        if(!loggedIn){
            login();
        }

        File opFile;
        String fileSelection;

        if (!initialized) {
            init();

            System.out.print("\f");
            System.out.println("WLTS [v0.3]");
            System.out.println("Created by: Jacob Dixon @jacobdixon.us");
        }

        initialized = true;

        Scanner scanner = new Scanner(System.in);
        System.out.print(defaultUsername + "@" + systemName + ":" + homeDIRs + "$ ");
        String s = scanner.nextLine();

        Matcher lsSRM         = Pattern.compile("^ls$").matcher(s);
        Matcher pwdSRM        = Pattern.compile("^pwd$").matcher(s);
        Matcher cdSRM         = Pattern.compile("^cd( [A-Za-z0-9-.]+)?$").matcher(s);
        Matcher mvSRM         = Pattern.compile("^mv [A-Za-z0-9-.]+ [A-Za-z0-9-.]+$").matcher(s);
        Matcher touchSRM      = Pattern.compile("^touch [A-Za-z.]+$").matcher(s);
        Matcher clearSRM      = Pattern.compile("^clear$").matcher(s);
        Matcher writeSRM        = Pattern.compile("^write [A-Za-z0-9-.]+$").matcher(s);
        Matcher catSRM        = Pattern.compile("^cat ([A-Za-z0-9-.]+)$").matcher(s);
        Matcher timeSRM       = Pattern.compile("^time$|time (-[u])$").matcher(s);
        Matcher exitSRM       = Pattern.compile("^exit$").matcher(s);
        Matcher helpSRM       = Pattern.compile("^help$").matcher(s);
        Matcher exeSRM        = Pattern.compile("^./([A-Za-z0-9.-]+)").matcher(s);
        Matcher anyCommandSRM = Pattern.compile("^[A-Za-z-]+").matcher(s);


        // This is art.

        if (lsSRM.find()) {
            Commands.list();

        } else if(pwdSRM.find()){
            Commands.pwd(homeDIR);

        } else if (cdSRM.find()) {
            Commands.noAccess();

        } else if (mvSRM.find()) {
            Commands.noAccess();

        } else if (touchSRM.find()) {
            fileSelection = s.substring(s.lastIndexOf("touch") + 6);
            Commands.touch(fileSelection);

        } else if (clearSRM.find()) {
            Commands.clear();

        } else if (writeSRM.find()) {
            fileSelection = s.substring(s.lastIndexOf("write ") + 4);
            opFile = findFile(fileSelection);

            Commands.write(opFile);

        } else if (catSRM.find()) {
            fileSelection = catSRM.group(1);
            opFile = findFile(fileSelection);

            Commands.cat(opFile);

        } else if (timeSRM.find()) {
            if(timeSRM.group(1)!= null) {
                Commands.getTime(timeSRM.group(1));
            }else{
                Commands.getTime("");
            }

        } else if (helpSRM.find()){
          Commands.help();

        } else if (exitSRM.find()) {
            return;

        } else if (s.equals("")){
            main(null);

        } else if (exeSRM.find()){
          fileSelection = exeSRM.group(1);
          opFile = findFile(fileSelection);

          if(opFile != FILE_NOT_FOUND && opFile.exe != null){
              Commands.executeProgram(opFile.exe);

          } else if (opFile.exe == null && opFile != FILE_NOT_FOUND){
              System.out.println("File is not executable.");

          } else if(opFile == FILE_NOT_FOUND){
              System.out.println("File not found.");

          } else{
              System.out.println("Error executing file.");
          }

            main(null);

        } else if (anyCommandSRM.find()){
            String tmp = anyCommandSRM.group();
            System.out.println("\""+ tmp + "\"" + " is not recognized as a command or executable program.");
            main(null);

        } else{
            System.out.println("\"" + s + "\"" + " is not recognized as a command or executable program.");
            main(null);
        }

    }

    public static void login(){

        String s;
        String inUserName;
        String inUserPass;

        System.out.println("\f");

        System.out.print("USERNAME: ");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        inUserName = s;
        System.out.print("\n");

        System.out.print("PASSWORD: ");
        s = scanner.nextLine();
        inUserPass = s;

        if(inUserName.equals(USERNAME) && inUserPass.equals(PASSWORD)){
            loggedIn = true;
            System.out.println();
        }else{
            System.out.println();
            System.out.println("Invalid  credentials.");
            try {
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            // return to main
            main(null);
        }


    }

    public static File findFile(String name){

        File result = FILE_NOT_FOUND;

        for(File file : files){
            if(file.name.equals(name)){
                result = file;
                return result;
            }
        }

        return result;
    }

}
