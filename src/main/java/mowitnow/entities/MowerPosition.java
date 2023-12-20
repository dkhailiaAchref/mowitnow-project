package mowitnow.entities;
public class MowerPosition {
	private Coordinates MowerCoordinates;
	private Params.Orientation MowerOrientation;

	public MowerPosition(Coordinates pCoordonneesTondeuse,
                         Params.Orientation pOrientationTondeuse) {
		this.MowerCoordinates = pCoordonneesTondeuse;
		this.MowerOrientation = pOrientationTondeuse;
	}

	public Params.Orientation getMowerOrientation() {
		return MowerOrientation;
	}

	public void setMowerOrientation(Params.Orientation pOrientationTondeuse) {
		this.MowerOrientation = pOrientationTondeuse;
	}

	public Coordinates getMowerCoordinates() {
		return MowerCoordinates;
	}

	public void setMowerCoordinates(Coordinates pCoordonneesTondeuse) {
		this.MowerCoordinates = pCoordonneesTondeuse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MowerCoordinates == null) ? 0 : MowerCoordinates.hashCode());
		result = prime * result + ((MowerOrientation == null) ? 0 : MowerOrientation.hashCode());
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
		MowerPosition other = (MowerPosition) obj;
		if (MowerCoordinates == null) {
			if (other.MowerCoordinates != null)
				return false;
			} else if (!MowerCoordinates.equals(other.MowerCoordinates))
				return false;
		if (MowerOrientation != other.MowerOrientation)
			return false;
		return true;
	}
}