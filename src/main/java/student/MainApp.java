package student;

import student.view.MainView;

public class MainApp {

     /** Private constructor to prevent instantiation. */
    private MainApp() {
        //empty
    }
    public static void main(String[] args) {
        /*
         * feel free to delete following line 
         * just a test for GUI setup
         */
        System.out.println("to set up gradle project, run: gradle init");
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }
    
}
