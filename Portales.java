
/**
 * @author andres felipe garcia bernal
 * @author yei hong 
 *
 */
public class Portales {

	/**
	 * posicion x
	 */
	private int x;
	/**
	 * posicion y
	 */
	private int y;

	public Portales(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public boolean esPortal(int x, int y)
	{
		if(x==this.x&&y==this.y) {
			return true;
		}
		else
			return false;
	}

	public Portales getPortal()
	{
		return this;
	}

}
