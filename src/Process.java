//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;
    Boolean isSuspended;

	
	public Process(int m) {
		processID = m;
		isSuspended=false;
	}
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}

	}
	
	private void process1() {
		OperatingSystem.printSem.semWait(this);
		OperatingSystem.inputSem.semWait(this);
		OperatingSystem.printText("Enter File Name: ");
		String fileName=OperatingSystem.TakeInput();
		OperatingSystem.printSem.semPost(this);
		OperatingSystem.inputSem.semPost(this);

		OperatingSystem.printSem.semWait(this);
		OperatingSystem.readSem.semWait(this);
		OperatingSystem.printText(OperatingSystem.readFile(fileName));
		setProcessState(this,ProcessState.Terminated);
		OperatingSystem.printSem.semPost(this);
		OperatingSystem.readSem.semPost(this);
		
		
		}
	
	private void process2() {
		OperatingSystem.printSem.semWait(this);
		OperatingSystem.inputSem.semWait(this);


		OperatingSystem.printText("Enter File Name: ");
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter Data: ");
		String data= OperatingSystem.TakeInput();

		OperatingSystem.printSem.semPost(this);
		OperatingSystem.inputSem.semPost(this);
		
		OperatingSystem.writeSem.semWait(this);
		OperatingSystem.writefile(filename,data);
		setProcessState(this,ProcessState.Terminated);
		OperatingSystem.writeSem.semPost(this);

		}
	private void process3() {
		OperatingSystem.printSem.semWait(this);
		int x=0;

		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}

		setProcessState(this,ProcessState.Terminated);
		OperatingSystem.printSem.semPost(this);

		}
	
	private void process4() {
		OperatingSystem.printSem.semWait(this);
		int x=500;
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		setProcessState(this,ProcessState.Terminated);
		OperatingSystem.printSem.semPost(this);

		}
	private void process5() {
		OperatingSystem.printSem.semWait(this);
		OperatingSystem.inputSem.semWait(this);
		
		OperatingSystem.printText("Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		
		OperatingSystem.printSem.semPost(this);
		OperatingSystem.inputSem.semPost(this);
		
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.writeSem.semWait(this);
		OperatingSystem.writefile("P5.txt", data);
		setProcessState(this,ProcessState.Terminated);
		OperatingSystem.writeSem.semPost(this);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
