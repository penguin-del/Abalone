package board;

public class Marble
{
	public enum MarbleColor 
	{
		INVALID(-2), EMPTY(-1), BLACK(0), WHITE(1);

		private MarbleColor(int value)
		{
		} 

		//flips color of given marble
		public MarbleColor flipColor() {
			if(this==MarbleColor.EMPTY) System.err.println("Attempting to flip an empty color Marble::flipColor");
			if(this==MarbleColor.INVALID)System.err.println("Attempting to flip an invalid color Marble::flipColor");
			if(this==MarbleColor.WHITE) return MarbleColor.BLACK;
			return MarbleColor.WHITE;
		}
		
	}
}







