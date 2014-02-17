package Topcoder;

public class SpeedRadar {

	public double averageSpeed(int minLimit, int maxLimit, int[] readings)
	{
		double X=0;
		int count = 0;
		double infringement = 0;
		for(int i:readings)
		{
			if(i>=minLimit && i<=maxLimit)
			{
				X=X+i;
				count++;
			}
			else
			{
				infringement++;
			}
		}
		infringement=(double) (infringement/readings.length)*100;
		if(infringement>10)
		{
			X = 0.0;
			return X;
		}
		else
		{
			X=X/count;
		}
		return X;
	}
}
/*    
Class:
SpeedRadar
Method:
averageSpeed
Parameters:
int, int, int[]
Returns:
double
Method signature:
double averageSpeed(int minLimit, int maxLimit, int[] readings)
(be sure your method is public)
Limits*/