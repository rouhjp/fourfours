package jp.rouh.fourfours.app;

import jp.rouh.fourfours.Expressions;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Application {
    private static final String TITLE = "Rouh/FourFours";
    private final JLabel result = new JLabel();
    private final JTextField field = new JTextField(20);

    public Application(){
        var frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(200, 200));

        var panel = new JPanel();
        panel.setLayout(new BorderLayout());

        result.setHorizontalAlignment(SwingConstants.CENTER);

        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateView();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateView();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateView();
            }

            private void updateView(){
                var expression = field.getText();
                var message = Expressions.evaluate(expression, err->"ERROR");
                if (message.equals("ERROR")){
                    result.setForeground(new Color(165, 42, 42));
                }else if (Expressions.isPerfect(expression)){
                    result.setForeground(Color.BLACK);
                }else{
                    result.setForeground(new Color(184, 134, 11));
                }
                result.setText(message);
            }
        });

        panel.add(result, BorderLayout.CENTER);
        panel.add(field, BorderLayout.SOUTH);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
