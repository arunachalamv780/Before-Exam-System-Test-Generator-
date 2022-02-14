package beforeexam;



import beforeexam.Models.Data;
import com.opencsv.CSVReader;
import java.awt.Color;
import java.io.*;
import java.io.FileReader;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.*;
class filechooser extends JFrame implements ActionListener {
    
    static JLabel l;
    static JTextArea a;
    static List<Data> questions = new ArrayList<>();
    static List<Data> optionA = new ArrayList<>();
    static List<Data> optionB = new ArrayList<>();
    static List<Data> optionC = new ArrayList<>();
    static List<Data> optionD = new ArrayList<>();
// public Random rand= new Random();
    public static void main(String args[])
    {
        
        JFrame f = new JFrame("file chooser");
        
        
        
        
        
        JButton b1 = new JButton("Open File");
        JButton b2 = new JButton("Start Test");
        filechooser f1 = new filechooser();
        
        
        b1.addActionListener(f1);
        
        
        JPanel p = new JPanel();
        f.add(p);
        p.setSize(500,500);
        p.setBackground(Color.white);
        //p.setAlignmentX(CENTER_ALIGNMENT);
        //p.setAlignmentY(CENTER_ALIGNMENT);
        //p.setBounds(100, 100,500,500);
        
        p.add(b1);
        p.add(b2);
        
        p.setVisible(true);
        l = new JLabel("no file selected");
        p.add(l);
        
        
        f.setResizable(false);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // f.show();
        b2.addActionListener(new ActionListener() {

            
            public void actionPerformed(ActionEvent e) {
              new TestFrame(questions);
            }
        }
        );
        
    }


    public void actionPerformed(ActionEvent e) 
    {
       
        
        String com = e.getActionCommand();
 
            if (com.equals("Open File")) {

            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            j.setAcceptAllFileFilterUsed(false);            
            j.setDialogTitle("Select a .csv file");
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv");
            j.addChoosableFileFilter(restrict);
 
            int r = j.showOpenDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION) {
                l.setText(j.getSelectedFile().getAbsolutePath());
                File fl=j.getSelectedFile();    
                String filepath=fl.getPath();  
                String Data;
                List<Data> dataList = new ArrayList<>();
                
                try{  
                    //BufferedReader br=new BufferedReader(new FileReader(filepath));    
                   
                    CSVReader reader = new CSVReader(new FileReader(filepath),',','"',1);
                    
                    String[] line;
                    String[] nextline = null;
                    while((line=reader.readNext())!=null)
                    {
                      
                    
                    if(line!=null)
                    {
                        //System.out.println(Arrays.toString(line));
                        Arrays.toString(line);
                        nextline = line;
                    }    
                    Data data= new Data();
                    //String values = {nextline};
                    String[] splittedValues= nextline[0].split(",");
                    data.setQuestions(nextline[1]);
                    data.setOptionA(nextline[2]);
                    data.setOptionB(nextline[3]);
                    data.setOptionC(nextline[4]);
                    data.setOptionD(nextline[5]);
                    data.setAnswer(nextline[6]);
                    dataList.add(data);
                    
                    }
        for(Data data: dataList){
            System.out.println(data.getQuestions()+"\t"+data.getOptionA()+"\t"+data.getOptionB()+"\t"+data.getOptionC()+"\t"+data.getOptionD()+"\t"+data.getAnswer());
            
        }
                    System.out.println("================================================================================================================");
                   Collections.shuffle( dataList );
                   
                    System.out.println("Random elements: ");
                    double size = dataList.size();
                    //floor = 2.8 - > 2 6.1 - > 6
                    //ceil = 2.8->3
                    
                    int n = (int) Math.ceil(size*0.25);
                    System.out.println(n);
                    for(int i=0;i<n;i++){
                        Data data = dataList.get(i);
                        questions.add(data);
                        optionA.add(data);
                        optionB.add(data);
                        optionC.add(data);
                        optionD.add(data);
                        System.out.println(data.getQuestions()+"\t"+data.getOptionA()+"\t"+data.getOptionB()+"\t"+data.getOptionC()+"\t"+data.getOptionD()+"\t"+data.getAnswer());
                    }
                     
      
                    reader.close();
                    }
            
                catch (FileNotFoundException ex) {
                    Logger.getLogger(filechooser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(filechooser.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (Exception ex) {ex.printStackTrace();  }

            
        }
                
         
                
    }    
        else
                l.setText("the user cancelled the operation"); 
            
    }
            
        }
    


