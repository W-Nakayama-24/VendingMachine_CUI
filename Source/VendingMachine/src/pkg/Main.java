package pkg;

public class Main {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();
        boolean systemContinueFlag = true;

        try {
            while (systemContinueFlag == true) {
                ui.display();
                ui.callFunction();
            }
        } catch (WrongProductNumberException ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println("予期せぬ例外が発生した為、システムを中断しました");
            System.out.println("管理者までご連絡ください");
            ex.printStackTrace();
        }
    }

}
