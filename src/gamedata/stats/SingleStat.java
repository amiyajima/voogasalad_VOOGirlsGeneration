package gamedata.stats;

import javafx.scene.image.ImageView;

public class SingleStat {
	private String myName;
	private double myValue;
	private ImageView myImgView;

	public SingleStat(String name, double value, ImageView imgView) {
		myName = name;
		myValue = value;
		myImgView = imgView;
	}

	public String getName() {
		return myName;
	}

	public double getValue() {
		return myValue;
	}
	
	public void setValue(double value) {
		myValue = value;
	}

	public ImageView getImageView() {
		return myImgView;
	}

	@Override
	public int hashCode() {
		return myName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SingleStat) {
			SingleStat other = (SingleStat) obj;
			if (other.myName.equals(this.myName)) {
				return true;
			}
		}
		return false;
	}
}
