package beforeexam;

import beforeexam.Models.Data;
import beforeexam.Models.SelectedValue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TestFrame {

    int i = 0;
    static List<Data> dataList = new ArrayList<>();
    static ButtonGroup bg = new ButtonGroup();
    static List<SelectedValue> values = new ArrayList<>();
    static List<Data> answer = new ArrayList<>();
     //List<SelectedValue> selectedList = new ArrayList<>();
String selectedAns;
    TestFrame(List<Data> questions) {
        JFrame jf = new JFrame();
        JLabel questionLabel;
        JRadioButton a, b, c, d;
        JButton next, previous, submit;
        jf.setTitle("Test Frame");
        JPanel jp = new JPanel();
        jf.add(jp);
        Map<Integer, String> svalue = new HashMap<>();
        questionLabel = new JLabel(questions.get(0).getQuestions());

        questionLabel.setBounds(50, 100, 1000, 50);
        questionLabel.setVisible(true);
        jp.add(questionLabel);
        a = new JRadioButton(questions.get(0).getOptionA());
        a.setBounds(75, 150, 300, 50);
        a.setBackground(Color.white);
        b = new JRadioButton(questions.get(0).getOptionB());
        b.setBounds(75, 200, 300, 50);
        b.setBackground(Color.white);
        c = new JRadioButton(questions.get(0).getOptionC());
        c.setBounds(75, 250, 300, 50);
        c.setBackground(Color.white);
        d = new JRadioButton(questions.get(0).getOptionD());
        d.setBounds(75, 300, 300, 50);
        d.setBackground(Color.white);
        this.bg.add(a);
        this.bg.add(b);
        this.bg.add(c);
        this.bg.add(d);
        jp.add(a);
        jp.add(b);
        jp.add(c);
        jp.add(d);
        
        previous = new JButton("Previous");
        previous.setBounds(50, 400, 100, 30);
        jp.add(previous);
        previous.setVisible(false);
        next = new JButton("Next");
        next.setBounds(300, 400, 100, 30);
        jp.add(next);
        submit = new JButton("Submit");
        submit.setBounds(300, 400, 100, 30);
        jp.add(submit);
        submit.setVisible(true);
        jf.setSize(600,600);
        jp.setSize(500, 500);
        jp.setLayout(null);
        jf.setVisible(true);
        jf.add(jp);
        jp.setBackground(Color.white);
        jp.setVisible(true);
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String ans = "";
                for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        ans = button.getText();
                        svalue.put(i, ans);
                    }
                }

                i++;
                System.out.println(ans);
                previous.setVisible(true);
                
                bg.clearSelection();
                //a.setDisabledIcon(null);
                if(bg.isSelected(null))
                {
                    bg.getSelection();
                    
                }
                

                if (i < questions.size()) {
                    questionLabel.setText(questions.get(i).getQuestions());
                    a.setText(questions.get(i).getOptionA());
                    b.setText(questions.get(i).getOptionB());
                    c.setText(questions.get(i).getOptionC());
                    d.setText(questions.get(i).getOptionD());
                }

                if (i == (questions.size()-1)) {
                    next.setVisible(false);
                    submit.setVisible(true);
                   
                }
            }

        });
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                i--;
                //bg.setSelected(null, true);
                //bg.clearSelection();
                e.getActionCommand();
                {
                    bg.setSelected(null, true);
                    next.setVisible(true);
                    a.setVisible(true);
               b.setVisible(true);
               c.setVisible(true);
               d.setVisible(true);
                }
                if (i < questions.size()) {

                    questionLabel.setText(questions.get(i).getQuestions());
                    a.setText(questions.get(i).getOptionA());
                    b.setText(questions.get(i).getOptionB());
                    c.setText(questions.get(i).getOptionC());
                    d.setText(questions.get(i).getOptionD());

                }
                if (i == 0) {
                    previous.setVisible(false);
                }
                a.setSelected(false);
                b.setSelected(false);
                c.setSelected(false);
                d.setSelected(false);
            }
        });
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                submit.setVisible(false);
                previous.setVisible(false);
                String ans = "";
                for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        ans = button.getText();
                        svalue.put(i, ans);
                    }
                } 
              svalue.forEach((k,v) -> System.out.println(k+"\t"+v));
              int correctAns =0;int wrongAnswer = 0;
               for(int i=0;i<questions.size();i++){
                   String selectedAns = svalue.get(i).split("\\)")[0].replace("(","");
                if(questions.get(i).getAnswer().equals(selectedAns)){
                    correctAns++;
                }
                else{
                    wrongAnswer++;
                }
            }
               questionLabel.setText("Correct answer :"+correctAns+"\t\t Wrong answer :"+wrongAnswer );
               a.setVisible(false);
               b.setVisible(false);
               c.setVisible(false);
               d.setVisible(false);
               
            }

        });

    }

    public static void main(String[] args) {

    }

}
