package student;

import student.model.FreeGameItem;

public class MainApp {

     /** Private constructor to prevent instantiation. */
    private MainApp() {
        //empty
    }
    public static void main(String[] args) {
        System.out.println("to set up gradle project, run: gradle init");
        /*
         * test for FreeGameItem class , feel free to delete this code block if you want
         */
        FreeGameItem item1 = new FreeGameItem(1, "Tarisland", "https://www.freetogame.com/g/582/thumbnail.jpg",
                "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
                "https://www.freetogame.com/open/tarisland", "MMORPG", "PC (Windows)", "Tencent",
                "Level Infinite", "2024-06-22", "https://www.freetogame.com/tarisland");
        FreeGameItem item2 = new FreeGameItem(2, "Overwatch 2", "https://www.freetogame.com/g/540/thumbnail.jpg",
                "A hero-focused first-person team shooter from Blizzard Entertainment.",
                "https://www.freetogame.com/open/overwatch-2", "Shooter", "PC (Windows)",
                "Activision Blizzard", "Blizzard Entertainment", "2022-10-04",
                "https://www.freetogame.com/overwatch-2");
        System.out.println(item1);
        System.out.println(item2);

        System.out.println(item1.equals(item2) + " " + String.valueOf(item1.hashCode() == item2.hashCode()));
    }
    
}
