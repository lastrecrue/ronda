package ronda.engine.elements;

public class CardValue {
	private byte number;

	public CardValue(byte n) {
		assert (n >= 1 && n <= 12 && n != 8 && n != 9);

		number = n;
	}

	public CardValue getNext() {
		if (number == 12)
			return null;

		byte n = (byte) (number + 1);
		if (n == 8) {
			n = 10;
		}
		return new CardValue(n);
	}

	public boolean isNext(CardValue cardValue) {
		CardValue nextThis = getNext();
		if (nextThis == null)
			return false;
		return nextThis.equals(cardValue);
	}

	@Override
	public int hashCode() {
		return number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardValue other = (CardValue) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public byte getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "CardValue [number=" + number + "]";
	}

}
