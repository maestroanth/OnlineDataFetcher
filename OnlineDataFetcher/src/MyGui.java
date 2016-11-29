import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MyGui extends JPanel implements ActionListener {


  private JLabel labelGirlsName;
  private JLabel labelWriter;
  private JLabel labelProducer;
  private JLabel labelReleaseDate;
  private JLabel labelDescription;
  private JLabel labelCurrentURL;
  
  private JTextField fieldGirlsName;
  private JTextField fieldWriter;
  private JTextField fieldProducer;
  private JTextField fieldReleaseDate;
  private JTextArea fieldDescription;
  private JTextField fieldCurrentURL;

  private JButton NextButton;

  public MyGui(Regex myGirlieRegex) {
	 
    makeTheObjects(myGirlieRegex);
    doTheLayout(); 
  } // end of constructor



private void makeTheObjects(Regex MySongRegex){

      //labelGirlsName = new JLabel("Girl's Name");
      labelWriter = new JLabel("Writer");
      labelProducer = new JLabel("Producer(s)");
      labelReleaseDate = new JLabel("Release Date");
      labelDescription = new JLabel("Description:");
      labelCurrentURL = new JLabel("Current URL");

      
      //fieldGirlsName = new JTextField("Nothing", 50);
      fieldWriter = new JTextField("Nothing", 50);
      fieldProducer = new JTextField("Nothing", 50);
      fieldReleaseDate = new JTextField("Nothing", 50);
      fieldDescription = new JTextArea(5,30);
      fieldCurrentURL = new JTextField("Nothing", 50);

      NextButton = new JButton("Start 100 Profile Scan"/*"Go To Next Profile"*/);
      /*
      NextButton.addActionListener( new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e){
              nextSong_actionPerformed(e);
              }
      });
 */
      //fieldGirlsName.setEditable(false);
      fieldWriter.setEditable(false);
      fieldProducer.setEditable(false);
      fieldReleaseDate.setEditable(false);
      fieldDescription.setEditable(false);
      fieldCurrentURL.setEditable(false);
      
      //presets
	    //fieldGirlsName.setText(MySongRegex.GirlsName);
	  	fieldWriter.setText(MySongRegex.SongWriter);
	  	fieldProducer.setText(MySongRegex.SongProducer);
	    fieldReleaseDate.setText(MySongRegex.SongReleaseDate);
	  	fieldDescription.setText(MySongRegex.ThirtyCharacters);
	  	fieldCurrentURL.setText(MySongRegex.NextURL);//this shows next URL atm
	

  }// end of creating objects method

  private void doTheLayout(){
	  JPanel gui = new JPanel(new BorderLayout(2,2));

      JPanel labelFields = new JPanel(new BorderLayout(2,2));
      labelFields.setBorder(new TitledBorder("Profiles Grabbed From The InterWeb"));

      JPanel labels = new JPanel(new GridLayout(0,1,1,1));

      JPanel fields = new JPanel(new GridLayout(0,1,1,1));
      

          //labels.add(labelGirlsName);
          labels.add(labelWriter);
          labels.add(labelProducer);
          labels.add(labelReleaseDate);
          labels.add(labelCurrentURL);

          //fields.add(fieldGirlsName);
          fields.add(fieldWriter);
          fields.add(fieldProducer);
          fields.add(fieldReleaseDate);
          fields.add(fieldCurrentURL);

      labelFields.add(labels, BorderLayout.CENTER);
      labelFields.add(fields, BorderLayout.EAST);

      JPanel guiCenter = new JPanel(new BorderLayout(2,2));
      guiCenter.setBorder(new TitledBorder("Description"));

      JPanel guiSouth = new JPanel();

      guiCenter.add(new JScrollPane(fieldDescription));

      gui.add(labelFields, BorderLayout.NORTH);
      gui.add(guiCenter, BorderLayout.CENTER);
      gui.add(guiSouth, BorderLayout.SOUTH);
     // guiSouth.add(NextButton);
      JOptionPane.showMessageDialog(null, gui, "Scanning Chicks From The InterWeb", JOptionPane.PLAIN_MESSAGE);

  }// end of Layout method

 // public void actionPerformed(ActionEvent e){System.out.println("should print to GUI");}
  
  void nextSong_actionPerformed(ActionEvent e){
	  
	  	Regex MySongRegex = new Regex();
        String url = "http://www.rollingstone.com/music/lists/the-500-greatest-songs-of-all-time-20110407/bob-dylan-like-a-rolling-stone-20110516";
        MySongRegex = NetGetter.getSourceCode(url);
        //fieldGirlsName.setText(MySongRegex.girlsName);
    	fieldWriter.setText(MySongRegex.SongWriter);
    	fieldProducer.setText(MySongRegex.SongProducer);
       	fieldReleaseDate.setText(MySongRegex.SongReleaseDate);
    	fieldDescription.setText(MySongRegex.ThirtyCharacters);
    	fieldCurrentURL.setText(MySongRegex.NextURL);//this shows next URL atm
    	MySongRegex = NetGetter.getSourceCode(MySongRegex.NextURL);
    	int numOfSongs = 100;
    	for(int i = 0; i < numOfSongs; i++)
        {
    		//fieldGirlsName.setText(MySongRegex.girlsName);
        	fieldWriter.setText(MySongRegex.SongWriter);
        	fieldProducer.setText(MySongRegex.SongProducer);
           	fieldReleaseDate.setText(MySongRegex.SongReleaseDate);
        	fieldDescription.setText(MySongRegex.ThirtyCharacters);
        	fieldCurrentURL.setText(MySongRegex.NextURL);//this shows next URL atm
        	MySongRegex = NetGetter.getSourceCode(MySongRegex.NextURL);
        	try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    	
   
     
  }// end of nextSong action event method





public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}

 

