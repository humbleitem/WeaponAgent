package timer;

import java.util.Timer;
import java.util.TimerTask;

import readFile.Attribute;
import thread.ComputeThread;
import thread.thread;

public class TimerStop extends thread {

	private Attribute AT;
	private ComputeThread computeThread;
	
	
	public TimerStop(Attribute AT,ComputeThread computeThread) {
		
		this.AT = AT;
		this.computeThread = computeThread;
		
	}
	
	@Override
	public void run() {
		
		
		long delayTime = (long)AT.getTotalTime()*1000;
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				computeThread.setKeepRun(false);
				timer.cancel();
				timer.purge();
				
			}
		}, delayTime);
		
	}
	
}
