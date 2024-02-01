package Main;

import Employees.PayrollSystem;
import FileHandler.FilesHandler;
import payroll_system.gui.CPS_Home;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException, IOException, URISyntaxException {

         FilesHandler.FileReader();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CPS_Home cps = new CPS_Home();
                cps.setVisible(true);

            }

        });

    }
}
