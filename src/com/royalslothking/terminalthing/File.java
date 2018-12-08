package com.royalslothking.terminalthing;

public class File{

    String name;
    String type;
    String size = "0B";
    String data;
    String exe;


    public File(String name, String type){
        this.name = name;
        this.type = type;
    }

    public File(String name, String type, String data){
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public File(String name, String type, boolean addToFS){
        this.name = name;
        this.type = type;
        if(addToFS) {
            Main.files.add(this);
        }
    }

    public File(String name, String type, String data, boolean addToFS){
        this.name = name;
        this.type = type;
        this.data = data;
        if(addToFS) {
            Main.files.add(this);
        }
    }

    public void assignEXE(String exe){
        this.exe = exe;
    }

}
