package projekt.base;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

//TODO H1.3
public class TimeInterval {
	private final LocalDateTime start;
	private final LocalDateTime end;
	
	/**
	 * Constructs a TimeInterval object representing a time interval
	 * 
	 * @param start start of time interval
	 * @param end end of time interval	 
	 * @throws NullPointerException when either start or end of interval is null
	 * @throws IllegalArgumentException when start is after end of interval
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
	 * Returns the time at the start of the interval
	 * @return start start of interval
	 */
	public LocalDateTime getStart() {
		return start;
	}
	
	/**
	 * Returns the time at the end of the interval
	 * @return end end of interval
	 */
	public LocalDateTime getEnd() {
		return end;
	}
	
	/**
	 * Returns the time interval between start and end
	 * @return the time interval between start and end
	 */
	public Duration getDuration() {
		return Duration.between(start.withSecond(0).withNano(0), end.withSecond(0).withNano(0));
	}
}
