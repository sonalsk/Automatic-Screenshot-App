import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class tasca 
{
	public static void main(String[] args) throws IOException, AWTException
	{
		introMsg();
		int[] var = inputVariables();
		int picSize = var[0];
		int time = var[1];
		runningScreenshot(picSize, time);
		outroMsg();
	}
	
	private static void introMsg() 
	{
		System.out.println("\n######################################################################\n");
		System.out.println("Welcome to Automatic Screenshot Capture!");	
		System.out.println("Developed by Piyush Sharma");
	}
	
	public static int[] inputVariables()
	{
		Scanner s = new Scanner(System.in);
		
		int picSize;
		while(true){
			System.out.println("\nHow many screenshots do you want to capture?");
			try {
				picSize = Integer.parseInt(s.next());
			}
			catch (NumberFormatException exception){
				System.out.println("ERROR: The number of screenshot must be in integers!");
				continue;
			}
			// error handling for size
			if ( picSize <= 0 ) {
				System.out.println("ERROR: Less than 1 screenshot can not be taken!");
				continue;
			}
			else if ( picSize > 100 ) {
				System.out.println("ERROR: More than 100 screenshot can not be taken!");
				continue;
			}
			else{
				break;
			}
		}
		
		int time;
		while(true){
			System.out.println("\nTime duration between each screenshot? [In Seconds] = ");
			try {
				time = Integer.parseInt(s.next());
			}
			catch (NumberFormatException exception){
				System.out.println("ERROR: The time duration must be in integers!");
				continue;
			}
			// error handling for size
			if ( time <= 0 ) {
				System.out.println("ERROR: Enter a time >= 1 second!");
				continue;
			}
			else if ( time > 3600 ) {
				System.out.println("ERROR: Enter a time <= 3600 second!");
				continue;
			}
			else{
				break;
			}
		}
		
		s.close();
		return new int[]{picSize, time};
	}
	
	public static void runningScreenshot(int picSize, int time) throws IOException, AWTException 
	{
		System.out.println("\nTASCA has started!\n");
		int total = picSize;
		while ( picSize > 0 ) {
			captureScreenShot(total-picSize);
			timeBreak(time);
			picSize--;
			System.out.println("Screenshot " + (total-picSize) + " has been saved in bin folder of project");
		}
	}

	public static void captureScreenShot(int num) throws IOException, AWTException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot r = new Robot();
		BufferedImage screenShot = r.createScreenCapture(screenRectangle);
		String filename = "screenshot"+(num+1)+".jpeg";
		ImageIO.write(screenShot, "jpeg", new File(filename));
	}
	
	public static void timeBreak(int time) throws AWTException
	{
		Robot r = new Robot();
		r.delay(time*1000);
	}
	
	private static void outroMsg() 
	{
		System.out.println("\nAll the screenshots have been saved!");
		System.out.println("Thank you for using the app!");
		System.out.println("\n######################################################################\n");
	}
}
