package pkg;

public class Main {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();
        boolean systemContinueFlag = true;
        while (systemContinueFlag == true) {
            ui.display();
            ui.callFunction();
        }

    }
}