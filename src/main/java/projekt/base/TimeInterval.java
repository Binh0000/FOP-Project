package projekt.base;
import java.time.Duration;
import java.time.LocalDateTime;

//TODO H1.3
public class TimeInterval {
	private final LocalDateTime start;
	private final LocalDateTime end;
	
	/**
	 * Constructs a TimeInterval object representing a time interval
	 * @param start start of time interval
	 * @param end end of time interval
	 */
	public TimeInterval(LocalDateTime start, LocalDateTime end) {
		if(start == null) 
			throw new NullPointerException("start");
		else if (end == null)
			throw new NullPointerException("end");
		else if(start.isAfter(end))
			throw new IllegalArgumentException("Start " + start.toString() + " is after end " + end.toString());
		
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Returns the private constant start
	 * @return start
	 */
	public LocalDateTime getStart() {
		return start;
	}
	
	/**
	 * Returns the private constant end
	 * @return end
	 */
	public LocalDateTime getEnd() {
		return end;
	}
	
	/**
	 * Returns the time interval between start and end
	 * @return
	 */
	public Duration getDuration() {
		return Duration.between(start, end);		
	}
}
