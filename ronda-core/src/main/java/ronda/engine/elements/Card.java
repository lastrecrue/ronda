package ronda.engine.elements;

public class Card {
	private final CardSymbol symbol;
	private final CardValue value;

	public Card(CardSymbol symbol, CardValue value) {
		this.symbol = symbol;
		this.value = value;
	}

	public CardSymbol getSymbol() {
		return symbol;
	}

	public CardValue getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (symbol != other.symbol)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value.getNumber() + symbol.toString().substring(0, 2);
	}

}
