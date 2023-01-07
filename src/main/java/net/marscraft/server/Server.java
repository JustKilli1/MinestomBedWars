package net.marscraft.server;

import java.io.File;

public class Server {

    public static void createFolderStructure() {
        File file = new File("instances");
        if(!file.exists()) file.mkdirs();
    }




}
