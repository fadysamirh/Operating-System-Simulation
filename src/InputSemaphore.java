import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class InputSemaphore {
	Queue<Process> queue;
	AtomicBoolean value;

	public InputSemaphore() {
		// TODO Auto-generated constructor stub
		queue = new LinkedList<Process>();
		value = new AtomicBoolean();
		value.set(true);

	}

	public void semWait(Process p) {
		if (value.compareAndSet(true, false)) {
			
		} else {
			queue.add(p);
			p.setProcessState(p, ProcessState.Waiting);
			p.isSuspended=true;
			p.suspend();

				
		}
		
	}

	public void semPost(Process p) {
		if (queue.isEmpty()) {
			value.set(true);
		} else {
			Process removed= queue.remove();
			removed.setProcessState(removed, ProcessState.Ready);
			OperatingSystem.readyQueue.add(removed);
		}
	}
}
