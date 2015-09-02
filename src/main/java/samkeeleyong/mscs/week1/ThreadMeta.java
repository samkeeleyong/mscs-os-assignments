package samkeeleyong.mscs.week1;

class ThreadMeta{
    int start;
    int end;
    Thread thread;
    static int number;
    int lastProcessedNumber;

    @Override
    public String toString(){
        return String.format("[%d - %d]", start, end);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
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
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
}