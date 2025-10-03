package src.main.java.windows;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import src.main.java.Models.Task;

public class Details extends JDialog {
    public Details(Task tl){

        WindowBase.getInstance().setEnabled(false);


        this.setLayout(new GridLayout(6, 2, 0,0));
        this.setName("Details");
        this.setSize(250, 400);
        JLabel title = new JLabel(tl.getTitle());
        JButton ok = new JButton("Ok");        
        ok.setAlignmentY(BOTTOM_ALIGNMENT);

        ok.addActionListener(e -> onClick());
        this.add(title);
        this.add(ok);
        this.setVisible(true);
    }
    private void onClick(){
        WindowBase.getInstance().setEnabled(true);
        dispose();
    }
}
