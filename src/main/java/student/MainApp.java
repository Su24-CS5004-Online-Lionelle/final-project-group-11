package student;

import student.controller.Controller;
import student.model.ItemModelImpl;
import student.view.MainView;


public class MainApp {

     /** Private constructor to prevent instantiation. */
    private MainApp() {
        //empty
    }
    public static void main(String[] args) throws Exception {
        /*
         * feel free to delete following line
         * just a test for GUI setup
         */
        System.out.println("to set up gradle project, run: gradle init");
        Controller obj = new Controller(new ItemModelImpl());
        MainView mainView = new MainView(obj);
        mainView.setVisible(true);
    }
    
}
