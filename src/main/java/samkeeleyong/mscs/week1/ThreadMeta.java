package samkeeleyong.mscs.week1;

import java.math.BigInteger;

class ThreadMeta{
    BigInteger start;
    BigInteger end;
    Thread thread;
    static BigInteger number;
    BigInteger lastProcessedNumber;
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThreadMeta other = (ThreadMeta) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

    @Override
    public String toString(){
        return String.format("[%s - %s]", start.toString(), end.toString());
    }	
}