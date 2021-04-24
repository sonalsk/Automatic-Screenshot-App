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

public class backend 
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
	
	private static void outroMsg() 
	{
		System.out.println("\nAll the screenshots have been saved!");
		System.out.println("Thank you for using the app!");
	}

	private static void introMsg() 
	{
		System.out.println("Welcome to Automatic Screenshot Capture!");	
		System.out.println("Developed by Piyush Sharma\n");
	}

	public static void captureScreenShot(int num) throws IOException, AWTException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot r = new Robot();
		BufferedImage screenShot = r.createScreenCapture(screenRectangle);
		String filename = "screenshot"+(num+1);
		String foldername = "C:\\Users\\Piyush Sharma\\Desktop\\images\\";
		ImageIO.write(screenShot, "jpeg", new File(foldername+filename));
	}
	
	public static void timeBreak(int time) throws AWTException
	{
		Robot r = new Robot();
		r.delay(time*1000);
	}

	public static int[] inputVariables()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("How many screenshots do you want to capture? = ");
		int picSize = s.nextInt();
		
		// error handling for size
		while ( picSize < 0 ) {
			System.out.println("ERROR: Less than 1 screenshot can not be taken!");
			System.out.println("How many screenshots do you want to capture? [Minimum 1 - Maximum 100] = ");
			picSize = s.nextInt();
		}
		while ( picSize > 100 ) {
			System.out.println("ERROR: More than 100 screenshot can not be taken!");
			System.out.println("How many screenshots do you want to capture? [Minimum 1 - Maximum 100] = ");
			picSize = s.nextInt();
		}
		// this does not work because int already stores the value and returns error
		while (picSize != (int)picSize) {
			System.out.println("ERROR: The number of screenshot must be in integers");
			System.out.println("How many screenshots do you want to capture? [Minimum 1 - Maximum 100] = ");
			picSize = s.nextInt();
		}
		
		System.out.println("Time duration between each screenshot? [In Seconds] = ");
		int time = s.nextInt();
		
		// error handling for time
		while ( time < 0 ) {
			System.out.println("ERROR: Enter a time >= 1 second!");
			System.out.println("Time duration between each screenshot? [In Seconds] = ");
			time = s.nextInt();
		}
		while ( time > 3600 ) {
			System.out.println("ERROR: Enter a time <= 3600 second!");
			System.out.println("Time duration between each screenshot? [In Seconds] = ");
			time = s.nextInt();
		}
		// this does not work because int already stores the value and returns error
		while (time != (int)time) {
			System.out.println("ERROR: The time duration must be in integers!");
			System.out.println("Time duration between each screenshot? [In Seconds] = ");
			time = s.nextInt();
		}
		
		s.close();
		int[] arr = {picSize, time};
		return arr;
	}
	
	public static void runningScreenshot(int picSize, int time) throws IOException, AWTException 
	{
		int total = picSize;
		while ( picSize > 0 ) {
			captureScreenShot(total-picSize);
			timeBreak(time);
			picSize--;
			System.out.println("Screenshot " + (total-picSize) + " has been saved");
		}
	}
}