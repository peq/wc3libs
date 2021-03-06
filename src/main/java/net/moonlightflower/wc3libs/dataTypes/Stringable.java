package net.moonlightflower.wc3libs.dataTypes;

public interface Stringable extends Comparable<Stringable> {
	@Override
	String toString();

	default int compareTo(Stringable o) {
		return this.toString().compareTo(o.toString());
	}
}
