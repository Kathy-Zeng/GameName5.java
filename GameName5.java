// Kathy Zeng
// 5/17/21
// GameName5.java
// Desciption: My game is called Geography Quiz with 10 questions.
// Source cited: EmptyBorder (Java Platform SE 7 ). (2020, June 24).
// Retrieved April 21, 2021, from https://docs.oracle.com/javase/7/docs
// api/javax/swing/border/EmptyBorder.html
// URL address: https://docs.oracle.com/javase/7/docs
// api/javax/swing/border/EmptyBorder.html

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameName5
{
	public static void main(String[] args) 
	{
		GameName5 gn5 = new GameName5();
		gn5.run();
	}

	// Creates a frame 600 by 600.
	public void run() 
	{
		JFrame frame = new JFrame ("GameName5");
		frame.setSize(620, 600);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GameName5Panel gn5p = new GameName5Panel();
		frame.getContentPane().add(gn5p);
		frame.setVisible(true);
		frame.setResizable(true);
	}
}

class GameName5Panel extends JPanel
{	

	public GameName5Panel()
	{
		CardLayout cards = new CardLayout();
		setLayout(cards);
		GameData data = new GameData();
		//data.grabQuestionFromFile();
		StartPage sp = new StartPage(this, cards);
		add(sp, "StartPage");
		Instructions is = new Instructions(this, cards);
		add(is, "InstructionsPage");
		GamePanel gp = new GamePanel(this, cards);
		add(gp, "GamePage");
		DifficultyPanel dp = new DifficultyPanel(this, cards);
		add(dp, "SelectADifficulty");
		PlayGamePanel pgp = new PlayGamePanel(data, this, cards, dp, gp);
		add(pgp, "PlayGamePanel");
		GameResultsPanel grp = new GameResultsPanel(data, this, cards);
		add(grp, "GameResultsPanel");
	}
}

// This is a class for a start panel with a null layout. Welcome
// message is above instructions, play game, and exit buttons.
// These handler classes are use to make them responsive. 
class StartPage extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName5Panel gameName; // Pass in as a parameter.
	private String worldPic; // Naming an image.
	private Image image; // Gets an image file.
	
	public StartPage(GameName5Panel gameNam5, CardLayout cardsIn)
	{
		gameName = gameNam5;
		cards = cardsIn;
		worldPic = new String("countriesoftheworld.png");
		image = null;
		setLayout(null);
		getAImage();
		JLabel welcome = new JLabel("Welcome to Geography Quiz", JLabel.CENTER);
		welcome.setBounds(5, 5, 200, 200);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setVerticalAlignment(JLabel.CENTER);
		add(welcome);
		JButton instructions = new JButton("Instructions");
		instructions.setBounds(200, 120, 300, 120);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setVerticalAlignment(JLabel.CENTER);
		add(instructions);
		JButton playGame = new JButton("Play Game");
		playGame.setBounds(200, 280, 300, 120);
		playGame.setHorizontalAlignment(JLabel.CENTER);
		playGame.setVerticalAlignment(JLabel.CENTER);
		add(playGame);
		JButton exit = new JButton("Exit");
		exit.setBounds(200, 400, 300, 120);
		exit.setHorizontalAlignment(JLabel.CENTER);
		exit.setVerticalAlignment(JLabel.CENTER);
		add(exit);
		StartHandler sh = new StartHandler();
		instructions.addActionListener(sh);
		PlayHandler ph = new PlayHandler();
		playGame.addActionListener(ph);
		ExitHandler eh = new ExitHandler();
		exit.addActionListener(eh);
	}

	// Loads an image which is a world map.
	public void getAImage()
	{
		try
		{
			image = ImageIO.read(new File(worldPic) );
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + worldPic + "can't be found.\n\n\n");
			e.printStackTrace();
		}
	}

	// Draws an image.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 620, 600, this);
	}
	
	class StartHandler implements ActionListener
	{
		// Goes to Instructions page.
		public void actionPerformed(ActionEvent evt)
		{
			cards.show(gameName, "InstructionsPage");
		}
	}

	class PlayHandler implements ActionListener
	{
		// Goes to game page by pressing play game button or start page
		// by pressing go back button.
		public void actionPerformed(ActionEvent evt)
		{
			
			String command = evt.getActionCommand(); 
			if(command.equals("Play Game") )
				cards.show(gameName, "GamePage");
			else if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
		}
	}

	class ExitHandler implements ActionListener
	{
		
		// Exits the game.
		public void actionPerformed(ActionEvent evt)
		{
			System.exit(1);
		}
	}
}

// Instructions' class has a border layout. A label is in north and
// a text area takes a remaining space with instructions.  
class Instructions extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName5Panel gameName; // Pass in as a parameter.
	
	public Instructions(GameName5Panel gameNameIn, CardLayout cardsIn)
	{
		cards = new CardLayout();
		gameName = gameNameIn;
		cards = cardsIn;
		setLayout(new BorderLayout() );
		
		JLabel ins = new JLabel("Read Instructions");
		add(ins, BorderLayout.NORTH);
		JTextArea ta = new JTextArea("Geography Quiz: There is 10 questions"
			+ " per game that a user's goal is to get all answers right\n"
			+ "in order to get 10 falling balls after game been finished."
			+ " A user needs to type a right answer, if \n not, a right answer"
			+ " is going to shown or a user click on a hint button.");
		add(ta, BorderLayout.CENTER);
		JButton back = new JButton("Go Back");
		add(back, BorderLayout.SOUTH);
		InstructHandler ih = new InstructHandler();
		back.addActionListener(ih);
	}

	class InstructHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent evt)
		{
			// When a user clicks on "Go Back" button, takes back to
			// start page.
			String command = evt.getActionCommand();
			if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
		}
	}
}


class GamePanel extends JPanel
{
	private JButton back;
	private CardLayout cards; // Pass in as a parameter.
	private GameName5Panel gameName; // Pass in as a parameter.
	private DifficultyPanel dp;
	private PlayGamePanel pgp;
	private GameResultsPanel gr;
	private String categoryNam;
	
	public GamePanel(GameName5Panel gameNameIn, CardLayout cardsIn)
	{
		gameName = gameNameIn;
		cards = cardsIn;
		setLayout(new BorderLayout() );
		JLabel category = new JLabel("Select at least one category");
		add(category, BorderLayout.NORTH);
		SelectPanel sp = new SelectPanel();
		add(sp, BorderLayout.CENTER);
		back = new JButton("Go Back");
		add(back, BorderLayout.SOUTH);
		JButton ok = new JButton("ok");
		add(ok, BorderLayout.SOUTH);
		GameHandler gh = new GameHandler();
		back.addActionListener(gh);
		ok.addActionListener(gh);
		categoryNam = new String("");
	}

	// A set of checkboxes are use for specific geographic categories.
	class SelectPanel extends JPanel
	{
		public SelectPanel()
		{
			setLayout(new GridLayout(3, 2) );
			JCheckBox select1 = new JCheckBox("cities");
			add(select1);
			JCheckBox select2 = new JCheckBox("capital cities");
			add(select2);
			JCheckBox select3 = new JCheckBox("beaches");
			add(select3);
			JCheckBox select4 = new JCheckBox("population");
			add(select4);
			JCheckBox select5 = new JCheckBox("landmarks");
			add(select5);
			JCheckBox select6 = new JCheckBox("parks");
			add(select6);
		}
	}

	class GameHandler implements ActionListener
	{
		// When a user clicks on "Go Back" button, takes to the start
		// page. When a user clicks on ok button, takes to a difficulty
		// panel.
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Go Back") )
				cards.show(gameName, "StartPage");
			else if(command.equals("ok") )
				cards.show(gameName, "SelectADifficulty");
		}

		public String getCategoryNam()
		{
			return categoryNam;
		}
	}
}

// Creates a difficulty panel with a grid layout.
class DifficultyPanel extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName5Panel gameName; // Pass in as a parameter.
	private ButtonGroup bg;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private Timer timer;
	private int initialTime;
	private String difficulties;

	public DifficultyPanel(GameName5Panel gm5pIn, CardLayout cardsIn)
	{
		gameName = gm5pIn;
		cards = cardsIn;
		bg = new ButtonGroup();
		easy = new JRadioButton("Easy");
		medium = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");
		setLayout(new BorderLayout() );
		JLabel select = new JLabel("Difficulty: ");
		add(select, BorderLayout.NORTH);

		SelectDifficulty sd = new SelectDifficulty();
		add(sd, BorderLayout.CENTER);
		JButton confirm = new JButton("ok");
		add(confirm, BorderLayout.SOUTH);
		PlayGameHandler pgh = new PlayGameHandler();
		confirm.addActionListener(pgh);
		initialTime = 120;
		difficulties = "Easy";
	}

	class SelectDifficulty extends JPanel
	{
		public SelectDifficulty()
		{
			JPanel rbPanel = new JPanel();
			setLayout(new GridLayout(3, 1) );
			rbPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 100, 50) );
			DifficultyHandler dh = new DifficultyHandler();
			bg.add(easy);
			bg.add(medium);
			bg.add(hard);
			easy.addActionListener(dh);
			medium.addActionListener(dh);
			hard.addActionListener(dh);
			rbPanel.add(easy);
			rbPanel.add(medium);
			rbPanel.add(hard);
			add(rbPanel, BorderLayout.CENTER);
		}	
	}

	class DifficultyHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			System.out.println("command: " + cmd);
			if(cmd.equals("Easy") )
			{
				difficulties = "Easy";
				initialTime = 120;
			}
			else if(cmd.equals("Medium") )
			{
				difficulties = "Medium";
				initialTime = 90;
			}
			else if(cmd.equals("Hard") )
			{
				difficulties = "Hard";
				initialTime = 60;
			}
			System.out.println("initial time = " + initialTime);
		}
	}
	
	class PlayGameHandler implements ActionListener
	{
		// When a user clicks on ok button takes directly to the next panel.
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("ok") )
				cards.show(gameName, "PlayGamePanel");
		}
	}

	public String getDifficultLevel()
	{
		return difficulties;
	}

	public int getInitialTime()
	{

		System.out.println("Inside getter method initial time = " + initialTime);
		
		return initialTime;
	}
}

// Array of random questions and an another to draw image.
/// Only need one timerhandler class for timer and playing around with it.
class PlayGamePanel extends JPanel
{
	private CardLayout cards; // Pass in as a parameter.
	private GameName5Panel gm5p; // Pass in as a parameter.
	//private GameData data;
	private JButton quit; 
	//private int initialTime;
	private int seconds;
	private String message;
	private JTextField answer; // Type answer as textfield
	//private JButton start;
	private long begin; // Begins the timer.
	private int[] answerSet; 
	private Image image; // Loads arrays of images
	private int elapsedSeconds;
	private String[][] names;
	private boolean timerRunning;
	private DifficultyPanel dp;
	private GameData data;
	private GamePanel gp;
	
	// Variables are initizalize.
	public PlayGamePanel(GameData dataIn, GameName5Panel gm5pIn, CardLayout cardsIn, DifficultyPanel dpIn,
		GamePanel gpIn)
	{
		data = dataIn;
		elapsedSeconds = 0;
		gm5p = gm5pIn;
		dp = dpIn;
		cards = cardsIn;
		gp = gpIn;
		setLayout(new BorderLayout() );
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		LabelInNorth lin = new LabelInNorth();
		TimerInEast tie = new TimerInEast();
		names = new String[2][5];
		GameButtonsSouthAndTextField gbsatf = new GameButtonsSouthAndTextField();
		PanelInCenter pic = new PanelInCenter();
		WestPanel wp = new WestPanel();
		quit = new JButton("Quit");
		// Put all the panels together
		add(lin, BorderLayout.NORTH);
		add(tie, BorderLayout.EAST);
		add(gbsatf, BorderLayout.SOUTH);
		add(pic, BorderLayout.CENTER);
		add(wp, BorderLayout.WEST);
		QuitHandler qh = new QuitHandler();
		quit.addActionListener(qh);
		add(quit, BorderLayout.WEST);
		answer = new JTextField("", 25);
		answerSet = new int[10];
	}

	// Puts a label on top of a game panel and start button is use for a 
	// timer object when user clicks on it.
	class LabelInNorth extends JPanel
	{
		public LabelInNorth()
		{
			JLabel guess = new JLabel("Guess a location");
			add(guess, BorderLayout.NORTH);
		}
	}
	
	// Creates the label for a timer and make timer to operate.
	class TimerInEast extends JPanel implements ActionListener
	{
		private int time;
		private int tenthSec;
		private int elapsedSeconds;
		private int elapsedMinutes;
		private double secondsDecimal, secondsDisplay;
		private Timer timer;
		private JButton start;

		// Use setPreferredSize to make a timer object fully visible.
		public TimerInEast()
		{
			setPreferredSize(new Dimension(165, 0) );
			initialValues();
			setLayout(new BorderLayout(1, 20) );
			JLabel timerLabel = new JLabel("\t\tTimer: ");
			add(timerLabel, BorderLayout.NORTH);
			timer = new Timer(100, this);
			start = new JButton("Start");
			start.addActionListener(this);
			this.add(start, BorderLayout.SOUTH);
		}

		// Initialize values for timer.
		public void initialValues()
		{
			tenthSec = elapsedSeconds = elapsedMinutes = 0;
			secondsDecimal = 0.0;
			time = 120;
		}

		// Creates timer object
		public void paintComponent( Graphics g )   
		{
			super.paintComponent ( g );
			g.setColor ( Color.BLUE.darker() );
			if(dp.getDifficultLevel().equals("Easy") && secondsDecimal == 0.0)
				time = 120;
			else if(dp.getDifficultLevel().equals("Medium") && secondsDecimal == 0.0)
				time = 90;
			else if(dp.getDifficultLevel().equals("Hard") && secondsDecimal == 0.0)
				time = 60;
			secondsDecimal = time - tenthSec / 10.0;
			secondsDisplay = secondsDecimal % 60; 	
			elapsedMinutes = (int)secondsDecimal / 60;
				
			g.drawString (elapsedMinutes + " minutes\n" + 
				String.format("%.1f", secondsDisplay) + " seconds" , 20, 140 );
			g.setColor ( Color.BLUE );
		}

		// Determine whether a timer run at right times.
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			if ( command != null )   
			{
				if ( command.equals("Start") )
				{
					System.out.println(dp.getDifficultLevel() );
					timerRunning = true;
					timer.start();
				}
			}

			if(secondsDisplay == 0 && elapsedMinutes == 0)
			{
				timer.stop();
				timerRunning = false;
				JTextArea lose = new JTextArea("I lose");
				add(lose, BorderLayout.NORTH);
				lose.setForeground(Color.RED);
			}
			if(timerRunning == true)
				tenthSec++;
			this.repaint();	
		}
		
	}

	// Question's label is located in a center top panel.
	class PanelInCenter extends JPanel
	{
		private JLabel question;
		
		public PanelInCenter()
		{
			questionsAndImages(); 
			question = new JLabel("Question: " + data.getQuestion() );
			add(question);
		}

		// Stores in 10 images. Continents are randomly generated.
		/// Use 2D array for images.
		public void questionsAndImages()
		{
			int[] num = new int[10];
			int counter = 0;
			String categoryNam = new String("beaches");
			String continentName = new String("");
			int numContinent = 1;
			numContinent = (int)(Math.random() * 5) + 1;
			if(numContinent == 1)
				continentName = "North America";
			else if(numContinent == 2)
				continentName = "South America";
			else if(numContinent == 3)
				continentName = "Europe";
			else if(numContinent == 4)
				continentName = "Asia";
			else if(numContinent == 5)
				continentName = "Oceania";
			else if(numContinent == 6)
				continentName = "Africa";

			for(int numQuestions = 0; numQuestions < 10; numQuestions++)
			{
				num[numQuestions] += 1;
			}
			counter++;
			Image[][] images = new Image[names[0].length][names[1].length];
			//images = new Image[names.length];
			/// Need nested for loops.
			for(int i = 0; i < names.length; i++)
			{
				for(int j = 0; j < images[i].length; j++)
				{
					if(numContinent == 1)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String[][] {{"sf.jpg", "ny.jpg", "nfalls.jpg", "la.jpg","sd.jpg"},
								{"nvalley.jpg", "lv.jpg", "tij.jpg", "ch.jpg", "sea.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"wdc.jpg", "ott.jpg", "mcity.jpg", "hav.jpg", "sanjose.jpg"},
								{"te.jpg", "bel.jpg", "pap.jpg", "san.jpg", "pc.jpg"}};
						}
						
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][] {{"bbeach.jpg", "bsur.jpg", "cmay.jpg", "hbeach.jpg", "lbeach.jpg"},
								 {"mbeach.jpg", "mibeach.jpg", "monbeach.jpg", "scbeach.jpg", "higgs.jpg"}};
						}
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"ca.jpg", "hawaii.jpg", "texas.jpg", "or.jpg", "wash.jpg"},
								{"nymap.jpg", "wyom.jpg", "colo.jpg", "al.jpg", "can.jpg"}};
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"gatewayarch.jpg", "mtrushmore.jpg", "sliberty.jpg", "whouse.jpg", "gbridge.jpg"},
								{"hdam.jpg", "lbell.jpg", "aisland.jpg", "dworld.jpg", "cgate.jpg"}};
						}
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"ynp.jpg", "yellownp.jpg", "znp.jpg", "gcnp.jpg", "gtnp.jpg"},
								{"snp.jpg", "dvnp.jpg", "mtrainer.jpg", "lcnp.jpg", "dtnp.jpg"}};
						}
					}
					else if(numContinent == 2)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String [][] {{"rio.jpg", "ush.jpg", "sanpedro.jpg", "elarg.jpg", "salta.jpg"},
								{"santafe.jpg", "cali.jpg", "santacruz.jpg", "boavista.jpg", "surce.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"bas.jpg", "ba.jpg", "lima.jpg", "sanchile.jpg", "monte.jpg"},
								{"bog.jpg", "quito.jpg", "cayenne.jpg", "caracas.jpg", "gtown.jpg"}};
						}
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][] {{"copacabana.jpg", "lopesbeach.jpg", "tortuga.jpg", "playa.jpg", "ica.jpg"},
								{"valparaíso.jpg", "concha.jpg", "sanandrés.jpg", "prainha.jpg", "itamambuca.jpg"}};
						}
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"brazil.jpg", "chile.jpg", "bolivia.jpg", "peru.jpg", "columbia.jpg"},
								{"venezuela.jpg", "ecuador.jpg", "guyana.jpg", "suriname.jpg", "paraguay.jpg"}}; 
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"sloaf.jpg", "mp.jpg", "if.jpg", "elmorro.jpg", "casapueblo.jpg"},
								{"hp.jpg", "petrohué.jpg", "tierradelfuego.jpg", "tutelarfigures.jpg", "lrcemetery.jpg"}};
						}
						// These images need to be fix.
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"manu.jpg", "losg.jpg", "tpark.jpg", "itatiaia.png", "elcajas.jpg"},
								{"cotopaxi.jpg", "machalilla.jpg", "desertwithwater.jpg", "huascaran.jpg", "andeannp.jpg"}};
						}
					}
					else if(numContinent == 3)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String[][] {{"ven.jpg", "stpeter.jpg", "barcelona.jpg", "naples.jpg", "bath.jpg"},
								{"lyb.jpg", "cork.jpg", "graz.jpg", "munich.jpg", "krakow.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"dublin.jpg", "london.jpg", "paris.jpg", "vienna.jpg", "athens.jpg"},
								{"sofia.jpg", "rome.jpg", "moscow.jpg", "minsk.jpg", "helsinki.jpg"}};
						}
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][] {{"myrtos.jpg", "fibeach.jpg", "kbeach.jpg", "hbeachnorway.jpg", "westbeach.jpg"},
								{"bbay.jpg", "for.jpg", "costabeach.jpg", "sanbeach.jpg", "smal.jpg"}};
						}
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"sland.jpg", "germany.jpg", "uk.jpg", "ireland.jpg", "finland.jpg"},
								{"norway.jpg", "austria.jpg", "russia.jpg", "bulgaria.jpg", "greece.jpg"}};
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"sh.jpg", "ncastle.jpg", "bpalace.jpg", "alps.jpg", "lmeseum.jpg"}, 
								{"col.jpg", "bigben.jpg", "tbridge.jpg", "atom.jpg", "etower.jpg"}};
						}
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"balloonpark.jpg", "bnp.jpg", "enp.jpg", "knp.jpg", "tnp.jpg"},
								{"cnp.jpg", "gnp.jpg", "pcnp.jpg", "cnkscotland.jpg", "cairngorms.jpg"}};
						}
					}

					else if(numContinent == 4)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String[][] {{"shanghai.jpg", "dubai.jpg", "mumbai.jpg", "istanbul.jpg", "lhasa.jpg"},
								{"hk.jpg", "macau.jpg", "kashgar.jpg", "singapore.jpg", "sr.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"beijing.jpg", "tokyo.jpg", "astana.jpg", "seoul.jpg", "kualalumpur.jpg"},
								{"pyongyang.jpg", "abudhabi.jpg", "ankara.jpg", "kathmandu.jpg", "bangkok.jpg"}};
						}
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][] {{"goa.jpg", "phuket.jpg", "pianemo.jpg", "pinkbeach.jpg", "matakingisland.jpg"},
								{"halongbay.jpg", "kelingking.jpg", "baisao.jpg", "pompom.jpg", "whitebeach.jpg"}};
						}
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"india.jpg", "china.jpg", "southkorea.jpg", "thailand.jpg", "northkorea.jpg"},
								{"burma.jpg", "nepal.jpg", "arab.jpg", "turkey.jpg", "kazakhstan.jpg"}};
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"greatwall.jpg", "tajmahal.jpg", "wuhantower.jpg", "mteverest.jpg", "fuji.jpg"},
								{"gobi.jpg", "mtd.jpg", "angkorwat.jpg", "petra.jpg", "fbcity.jpg"}};
						}
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"skidubai.jpg", "hkdisney.jpg", "olypark.jpg", "waterpark.jpg", "waterpark2.jpg"},
								{"waterpark3.jpg", "fujipark.jpg", "cutepark.jpg", "animalpark.jpg", "inbeach.jpg"}};
						}
					}
					else if(numContinent == 5)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String[][] {{"sydney.jpg", "gc.jpg", "akland.jpg", "rot.jpg", "cchurch.jpg"},
								{"dune.jpg", "qtown.jpg", "mel.jpg", "perth.jpg", "pmoresby.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"canberra.jpg", "wellington.jpg", "noumea.jpg", "alofi.jpg", "papeete.jpg"},
								{"suva.jpg", "pagopago.jpg", "adamstown.jpg", "nuku'alofa.jpg", "majuro.jpg"}};
						}
						
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][]{{"bondibeach.jpg", "fmbeach.jpg", "nmbeach.jpg", "spbeach.jpg", "manlybeach.jpg"},
								{"whitehavenbeach.jpg", "rbay.jpg", "seventyfivebeach.jpg", "moobeach.jpg", "cbeach.jpg"}};
						}
						
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"aust.jpg", "nz.jpg", "fiji.jpg", "niue.jpg", "newcal.jpg"},
								{"tah.jpg", "pisland.jpg", "guam.jpg", "masisland.jpg", "tonga.jpg"}};
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"gbreef.jpg", "uluru.jpg", "sohouse.jpg", "pinklake.jpg", "lava.jpg"},
								{"jellylake.jpg", "hut.jpg", "bblagoon.jpg", "mountain.jpg", "fisland.jpg"}};
						}
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"mtcook.jpg", "bmount.jpg", "cbay.jpg", "cnationalpark.jpg", "dtnpark.jpg"},
								{"fnp.jpg", "fnpark.jpg", "kakadu.jpg", "nambung.jpg", "rockpark.jpg"}};
						}

					}
					else if(numContinent == 6)
					{
						if(categoryNam.equals("cities") )
						{
							names = new String[][] {{"casa.jpg", "marr.jpg", "johnaburg.jpg", "cot.jpg", "brazzaville.jpg"},
								{"bamako.jpg", "kumasi.jpg", "mombasa.jpg", "nouakchott.jpg", "bujumbura.jpg"}};
						}
						else if(categoryNam.equals("capital cities") )
						{
							names = new String[][] {{"cairo.jpg", "rabat.jpg", "lagos.jpg", "deer.jpg", "vic.jpg"},
								{"tunis.jpg", "juba.jpg", "lome.jpg", "nairobi.jpg", "tripoli.jpg"}};
						}
						
						else if(categoryNam.equals("beaches") )
						{
							names = new String[][] {{"zanzibar.jpg", "campsbay.jpg", "plett.jpg", "machangulo.jpg", "diani.jpg"}, 
								{"sharm.jpg", "watamu.jpg", "chitimba.jpg", "anse.jpg", "bellemare.jpg"}};
						}
						else if(categoryNam.equals("population") )
						{
							names = new String[][] {{"egypt.jpg", "morocco.jpg", "safrica.jpg", "tanzania.jpg", "nigeria.jpg"},
								{"chad.jpg", "mali.jpg", "niger.jpg", "cameroon.jpg", "congo.jpg"}};
						}
						else if(categoryNam.equals("landmarks") )
						{
							names = new String[][] {{"mtkili.jpg", "baobab.jpg", "pyramid.jpg", "tmountain.jpg", "tim.jpg"},
								{"cocodemer.jpg", "vicfalls.jpg", "namib.jpg", "nriver.jpg", "delta.jpg"}};
						}
						else if(categoryNam.equals("parks") )
						{
							names = new String[][] {{"serengeti.jpg", "etosha.jpg", "kruger.jpg", "laken.jpg", "virunga.jpg"},
								{"ngorongoro.jpg", "amboseli.jpg", "hwange.jpg", "lakemanyara.jpg", "namib-n.jpg"}};
						}
					}
				System.out.print("names[" + i + "] [" + j + "] = ");
				System.out.println(names[i][j]);
				
				// Declare a method to load images.
				images[i][j] = loadImages(names[i][j]);
				}
			} 
		}

		// Loads arrays of images.
		public Image loadImages(String pictName)
		{
			image = null;
			for(int j = 0; j < names.length; j++)
			{
				try
				{
					image = ImageIO.read(new File(pictName) );
				}
				catch(IOException e)
				{
					System.err.println("\n\n\n" + pictName + "can't be found.\n\n\n");
					e.printStackTrace();
				}
			}
			return image;
		}

		// Draws arrays of images.
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(image, 50, 50, 350, 400, this);
			//repaint();
		}
	}
	
	// Instantiates hint and enter button and textfield to let a user
	// type an answer.
	class GameButtonsSouthAndTextField extends JPanel implements ActionListener
	{
		private JTextField answer;
		private boolean correctAnswer;
		
		public GameButtonsSouthAndTextField()
		{
			answer = new JTextField("Enter answer here", 25);
			answer.setEditable(true);
			answer.addActionListener(this);
			add(answer, BorderLayout.CENTER);
			correctAnswer = false;
			//JButton enter = new JButton("Enter");
			//add(enter, BorderLayout.EAST);
			//resetQuestion();
		}

		// Use loop to go to next question
		/*public void resetQuestion ( )
		{
			for(int i = 0; i < answerSet.length; i++)
			{
				answerSet[i].setEnabled(true);
				answerSet[i].setBackground(new Color(230, 230, 230));
			}
			answerSet[data.getCorrectAnswer()].setBackground(Color.GREEN);
			for(int i = 0; i < answerSet.length; i++)
			{
				if(answerSet[i].isSelected())
				{
					if(i != data.getCorrectAnswer())
					{
						answerSet[i].setBackground(Color.RED);
					}
				}
			}
		}*/

		/// Get answer from textfield and using getter methods and questions
		public void actionPerformed(ActionEvent evt)
		{
			int time = 1;
			if(dp.getDifficultLevel().equals("Easy") )
				time = 120;
			else if(dp.getDifficultLevel().equals("Medium") )
				time = 90;
			else if(dp.getDifficultLevel().equals("Hard") )
				time = 60;
			int remainingSeconds = 1;
			remainingSeconds = time - remainingSeconds;
			String answers = answer.getText();
			System.out.println("text says " + answers);
			if(correctAnswer == false)
			{
				answer.setText("Wrong answer, the correct answer is " + data.getAnswer() 
					+ "in " + remainingSeconds + " seconds.");
				answer.setEditable(false);
			}
			else
			{
				answer.setText("Answer received is correct in " + remainingSeconds + " seconds.");
			}
		}
	}

	// Quit button is located in WestPanel.
	class WestPanel extends JPanel
	{
		public WestPanel()
		{
			quit = new JButton("Quit");
			add(quit, BorderLayout.NORTH);
		}
	}

	// Makes quit button works.
	class QuitHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String cmd = evt.getActionCommand();
			if(cmd.equals("Quit") )
				cards.show(gm5p, "StartPage");
		}
	}
}

// D & I labels and buttons in the constructer. Displays correct and
// wrong answers with a number of scores.
class GameResultsPanel extends JPanel
{
	private GameName5Panel gm5p; // Pass in as a parameter.
	private CardLayout cards; // Pass in as a parameter.
	private GameData data;
	private int scores; // Count scores base on correctness.
	private String [] answerSet; 
	private boolean wrongAnswer;
	private int wrongAn;

	/// Need File IO to store in scores.
	// Add all the components.
	// 5 points for each answer
	public GameResultsPanel(GameData dataIn, GameName5Panel gm5pIn, CardLayout cardsIn)
	{
		data = dataIn;
		gm5p = gm5pIn;
		cards = cardsIn;
		data = dataIn;
		scores = 0;
		setLayout(new BorderLayout() );
		scores++;
		wrongAnswer = false;
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(5, 1) );
		JLabel numScores = new JLabel("Number of scores earned: " + scores);
		center.add(numScores);
		JLabel correctAns = new JLabel("Correct Answer: " + data.getCorrectAnswer() );
		center.add(correctAns);
		wrongAn = 0;
		JLabel wrongAns = new JLabel("Wrong Answer: " + wrongAn);
		add(wrongAns);
		JButton playAgain = new JButton("Play Again");
		add(playAgain);
		JButton exit = new JButton("Exit");
		add(exit);
		scoresAndAnswers();
		// Add these buttons to an instance of a handler class to
		// make them responsive.
		GameResultsHandler grh = new GameResultsHandler();
		playAgain.addActionListener(grh);
		exit.addActionListener(grh);
		LeftBallPanel lbp = new LeftBallPanel();
		add(lbp, BorderLayout.WEST);
		RightBallPanel rbp = new RightBallPanel();
		add(rbp, BorderLayout.EAST);
	}

	// When a user get a right answer, adds 5 points and if not 5 points
	// off.
	public void scoresAndAnswers()
	{
		if(wrongAnswer == true)
			scores -= 5;
		else
			scores += 5;
		wrongAn = Math.abs(scores - data.getCorrectAnswer() );
	}
	
	// When a user presses play again button, it takes to game page.
	// When a user presses exit button, it exits the game.
	class GameResultsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String commands = evt.getActionCommand();
			if(commands.equals("Play Again") )
				cards.show(gm5p, "GamePage");
			else if(commands.equals("Exit") )
				System.exit(2);
		}
	}

	class LeftBallPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int y = 0; y < 50; y += 50)
			{
				for(int x = 10; x <= 5; x += 100)
				{
					g.fillOval(x, y, 50, 50);
				}
			}
		}
	}

	class RightBallPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int y = 0; y < 50; y += 50)
			{
				for(int x = 10; x <= 5; x += 100)
				{
					g.fillOval(x, y, 50, 50);
				}
			}
		}
	}
}

// Stores in questions and scores to files.
class GameData
{
	private String question;
	private int correctAnswer;
	private boolean [] chosenQuestions;
	private int questionCount;
	private int correctCount, lastGameCorrectCount;
	private Scanner inFile; // Reads a file
	private String scoreAndQuestionFile; // Appends scores
	private String value; // Write to a file.
	private PrintWriter pw; // Write to a file.
	private String updateScoreFile; // Appends scores
	private String answerIt;

	public GameData()
	{
		correctCount = 1;
		inFile = null;
		scoreAndQuestionFile = new String("scoresandquestions.txt");
		pw = null;
		value = "";
		updateScoreFile = new String("updateScores.txt");
		openFile();
		createFile();
		//createScores();
		//resetAll();
	}

	public void resetAll()
	{
		answerIt = "";
		question = "";
		correctAnswer = -1;
		chosenQuestions = new boolean[10];
		questionCount = correctCount = 1;
	}

	// Opens a file.
	public void openFile()
	{
		File scoreAndQuestion = new File(scoreAndQuestionFile);
		try
		{
			inFile = new Scanner(scoreAndQuestion);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("\n\n\n" + scoreAndQuestionFile + " can't be opened.\n\n\n");
			System.exit(1);
		}
	}

	// Appends/creates a file.
	public void createFile()
	{
		File updateScore = new File(updateScoreFile);
		try
		{
			pw = new PrintWriter( new FileWriter(updateScore, true) );
		}

		catch(IOException e)
		{
			System.err.println("\n\n\n" + updateScoreFile + " can't be created.\n\n\n");
			System.exit(2);
		}
	}

	// Appends scores after every game been played, closes a printwriter,
	// and file.  
	public void grabQuestionFromFile()
	{
		String result = "";
		int questionNumber = (int)(Math.random() * 360); 
		while(chosenQuestions[questionNumber] == true)
		{
			questionNumber = (int)(Math.random() * 360);
		}
		chosenQuestions[questionNumber] = true;
		for(int i = 0; i < 10; i++)
		{
			questionNumber++;
		}
		
		questionCount++;
		int counter = 0;
		
		while(inFile.hasNext() && counter < 2 * questionNumber)
		{
			String line = inFile.nextLine();
			counter++;
		}
		question = inFile.nextLine();
		counter = 0;
		while(inFile.hasNext() && counter < 1)
		{
			answerIt = inFile.nextLine();
			counter++;
		}

		while(inFile.hasNext()) 
		{
			String line = inFile.nextLine();
			if((line.indexOf("/") != -1) )
			{
				result += correctCount + "/50\n";
			}
			result += line + "\n";
		}
	}

	public String getQuestion( )
	{
		return "" + questionCount + ".\n" + question;
	}
						
	public String getAnswer()
	{
		return answerIt;
	}
						
	public int getCorrectAnswer( )
	{
		return correctAnswer;
	}
						
	public int getQuestionCount( )
	{
		return questionCount;
	}

	public String getScores ( )
	{
		String result = "";
		
		while(inFile.hasNext() )
		{
			value = inFile.next();
			pw.append(value);
		}
		while(inFile.hasNext()) 
		{
			String line = inFile.nextLine();
			result += line + "\n";
		}
		inFile.close();
		pw.close();
		return result;
	}
}
