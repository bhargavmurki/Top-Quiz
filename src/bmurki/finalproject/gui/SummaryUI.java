package bmurki.finalproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**

 SummaryUI class displays the final score, number of questions answered,

 number of correct answers and a bar chart showing the performance of each topic.

 It also provides options to play again, start a new game or exit.
 */
public class SummaryUI extends JPanel{

	// Member controls
	private JLabel lblTotalScore;
	private JLabel lblAttempted;
	private JLabel lblCorrect;
	private JLabel lblThank;
	private JLabel lblInfo;

	private JButton btnPlayAgain;
	private JPanel playAgainPane;

	// Score statistics collection
	private Map<String,Double> statistics=new HashMap<String,Double>();

	/**

	 Constructor for SummaryUI class.

	 @param summary A ScoreSummary object containing the user's score statistics.
	 */
	public SummaryUI(ScoreSummary summary)
	{
		statistics=summary.getStatistics();

		setLayout(new BorderLayout());

// Initialize and style labels
		lblThank=new JLabel("<html>Please see you results below<br/></html>",SwingConstants.CENTER);
		lblTotalScore=new JLabel( summary.getUserName() + "'s score is: "+summary.getTotalScore());
		lblAttempted=new JLabel("Questions Answered: "+summary.getTotalQuestions());
		lblCorrect=new JLabel("Correct Answers: "+summary.getCorrectAnswers());
		lblInfo=new JLabel();

// Set font and color for labels
		lblThank.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblThank.setForeground(new Color(0, 0, 0));
		lblTotalScore.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTotalScore.setForeground(new Color(0, 0, 0));
		lblAttempted.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAttempted.setForeground(new Color(0, 0, 0));
		lblCorrect.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCorrect.setForeground(new Color(0, 0, 0));
		lblInfo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblInfo.setForeground(new Color(0, 0, 0));

// Set info text based on total score
		if(summary.getTotalScore()==0)
		{
			lblInfo.setText("<html>Try Harder!</html>");
			lblInfo.setForeground(Color.RED);
		}
		else{
			lblInfo.setText("<html>Here is your performance in each of the topics you answered</html>");
			lblInfo.setForeground(new Color(0, 0, 0));
		}

// Create final score panel
		JPanel finalScorePane = new JPanel();
		finalScorePane.setLayout(new BoxLayout(finalScorePane, BoxLayout.Y_AXIS));
		finalScorePane.setPreferredSize(new Dimension(500,200));

// Add labels to final score panel
		finalScorePane.add(lblThank);
		finalScorePane.add(lblTotalScore);
		finalScorePane.add(lblAttempted);
		finalScorePane.add(lblCorrect);
		finalScorePane.add(lblInfo);

// Create graph panel
		JPanel graphPane=new JPanel();
		graphPane.setLayout(new BorderLayout());
		graphPane.setAlignmentX(CENTER_ALIGNMENT);
		graphPane.setPreferredSize(new Dimension(600,350));
		
		BarChart chart = new BarChart("Performance Chart");
		
		chart.setAlignmentX(CENTER_ALIGNMENT);
		Color[] colors={Color.red,Color.green,Color.orange,Color.blue,Color.magenta,Color.yellow, Color.cyan, Color.pink, Color.gray, Color.lightGray, Color.darkGray, Color.black};
		for(Entry<String, Double> topicScore:statistics.entrySet())
		{
			Random rand = new Random();
			int i = rand.nextInt(6);
			chart.addBar(colors[i], topicScore.getKey()+":"+String.format("%.2f", topicScore.getValue()));//format used is subject:percentScore
			
		}
		
		graphPane.add(chart,BorderLayout.CENTER);//add chart to pane
		
		//add to layout
		
		add(finalScorePane,BorderLayout.NORTH);
		add(graphPane,BorderLayout.CENTER);
		
		
		//play again option
		playAgainPane=new JPanel();
		playAgainPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPlayAgain=new JButton("");
		btnPlayAgain.setIcon(new ImageIcon("./Resources/LayoutImages/button_replay.png"));
		btnPlayAgain.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPlayAgain.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayAgain.setBorderPainted(false);
		btnPlayAgain.setContentAreaFilled(false);
		btnPlayAgain.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnPlayAgain.setForeground(new Color(0, 0, 139));
		btnPlayAgain.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// start over again
				if (e.getSource() == btnPlayAgain) {

				    Frame[] fr=JFrame.getFrames();
				    for(Frame f:fr)
				    {
				    	f.dispose();
				    }
				    new MainFrameUI();
				}
				
			}
		});

		JButton newgame = new JButton("");
		newgame.setBorderPainted(false);
		newgame.setContentAreaFilled(false);
		newgame.setIcon(new ImageIcon("./Resources/LayoutImages/button_new-game.png"));

		newgame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// start over again
				if (e.getSource() == newgame) {

					Frame[] fr=JFrame.getFrames();
					for(Frame f:fr)
					{
						f.dispose();
					}
					new UserPageUI();
				}
			}
		});

		JButton exitQuiz = new JButton("");
		exitQuiz.setBorderPainted(false);
		exitQuiz.setContentAreaFilled(false);
		exitQuiz.setIcon(new ImageIcon("./Resources/LayoutImages/button_exit.png"));

		exitQuiz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// start over again
				if (e.getSource() == exitQuiz) {
					System.exit(0);
				}
			}
		});

		playAgainPane.add(btnPlayAgain);
		playAgainPane.add(newgame);
		playAgainPane.add(exitQuiz);
		//show Try again button
    	playAgainPane.setVisible(true);
    	add(playAgainPane,BorderLayout.SOUTH);//add to layout
	}
	

}
/**

 BarChart class is a custom JPanel used to draw a bar chart with a title and labels.

 It displays the performance of each topic in the form of colored bars with the corresponding score percentage.
 */
class BarChart extends JPanel
{
	private String title;
	private Map<Color, String> bars = new LinkedHashMap<Color, String>();

	/**

	 Constructor for the BarChart class.
	 @param title The title displayed at the top of the bar chart.
	 */
	BarChart(String title)
	{
		this.title = title;
	}
	/**

	 Adds a bar to the bar chart with the specified color and value.
	 @param color The color of the bar.
	 @param value The value for the bar, in the format "Subject:scorePercent".
	 */
	public void addBar(Color color, String value)
	{
		bars.put(color, value);
		repaint();
	}
	/**

	 Paints the bar chart on the JPanel with bars, title, and labels.

	 @param g The Graphics object used to paint the bar chart.
	 */
	protected void paintComponent(Graphics g)
	{
		try {
			double max = Double.MIN_VALUE;
			for (String value : bars.values()) { //value is of format: Subject:scorePercent
				double score = Double.parseDouble(value.split(":")[1]);
				max = Math.max(max, score);
			}
			// Paint bars
			int maxWidth = 600;
			int maxHeight = 300;

			// Title and subject label styles
			Font titleFont = new Font("Times New Roman", Font.BOLD, 20);
			FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
			Font labelFont = new Font("Times New Roman", Font.BOLD, 15);
			FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

			int titleWidth = titleFontMetrics.stringWidth(title);
			int q = titleFontMetrics.getAscent();
			int p = (maxWidth - titleWidth) / 2;
			g.setFont(titleFont);

			int width = (maxWidth / bars.size()) - 2;
			int x = 1;
			for (Color color : bars.keySet()) {
				String subject = bars.get(color).split(":")[0];
				double value = Double.parseDouble(bars.get(color).split(":")[1]);

				// Handle 0 score condition
				int height = (value == 0) ? 1 : (int) ((maxHeight - q - 20) * (value / max));

				g.setColor(color);
				g.fillRect(x, maxHeight - height, width, height);
				g.setColor(Color.black);

				g.drawRect(x, maxHeight - height, width, height); // Draw bar

				g.setFont(labelFont);
				g.drawString(subject + " - " + value + "%", x, maxHeight - height); // Draw label

				x += (width + 5);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**

	 Returns the preferred size of the BarChart component.
	 @return A Dimension object representing the preferred size of the BarChart.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 10 + 2, 50);
	}
}