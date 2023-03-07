package com.ydp.rnb.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ydp.rnb.views.UserView;

public class UserViewUtils {

	private int myvar1 = 1;
	private int myvar2 = 2;
	private int myvar3 = 3;
	
	
	public static String getCompleteName(UserView uv) {
		return uv.getFirstName() + uv.getLastName();
	}

	public static boolean checkIfMarioRossi(UserView uv) {
		if (uv.getFirstName() == "Mario" && uv.getLastName() == "Rossi") {
			return true;
		}
		return false;
	}

	public static UserView getMarioSpecialFriend() {
		return new UserView(2, "luigi", "verdi");
	}

	public static String notMarioMessage() {
		return "not mario";
	}

	public static int enviousMethod() {
		int a = EnviedObject.enviedField1 + EnviedObject.enviedField2;
		EnviedObject.enviedField3++;
		EnviedObject.enviedField4++;
		for (int i = 0; i < a; i++) {
			EnviedObject.enviedField5 += EnviedObject.enviedField3;
			EnviedObject.enviedField3++;
			EnviedObject.enviedField4++;
		}
		
		int b = EnviedObject2.enviedField1 + EnviedObject2.enviedField2;
		EnviedObject2.enviedField3++;
		EnviedObject2.enviedField4++;
		
		for (int i = 0; i < b; i++) {
			EnviedObject3.enviedField1++;
			EnviedObject3.enviedField2++;
		}
		
		int c = EnviedObject3.enviedField3 + EnviedObject3.enviedField4 + EnviedObject3.enviedField5;
		c *= EnviedObject3.enviedField1;
		c *= EnviedObject3.enviedField2;
		
		return c + sum();
	}

	public static int sum() {
		return EnviedObject.enviedField1 + EnviedObject.enviedField2 + EnviedObject.enviedField3
				+ EnviedObject.enviedField4 + EnviedObject.enviedField5;
	}
	int lineCount = 4;
	int foldLevel = 2;
	int logl = 2;
	EnviedObject eo = new EnviedObject();
	public int getFoldLevel(int line)
	  {
	    if(line < 0 || line >= lineCount)
	      throw new ArrayIndexOutOfBoundsException(line);
	    if(eo instanceof EnviedObject)
	      return 0;
	    int firstInvalidFoldLevel = eo.enviedField1;
	    if(firstInvalidFoldLevel == -1 || line < firstInvalidFoldLevel)
	    {
	      return eo.enviedField2;
	    }
	    else
	    {
	      if(logl > 1)
	        System.out.println("Invalid fold levels from " + firstInvalidFoldLevel + " to " + line);
	      int newFoldLevel = 0;
	      boolean changed = false;
	      int firstUpdatedFoldLevel = firstInvalidFoldLevel;
	      for(int i = firstInvalidFoldLevel; i <= line; i++)
	      {
	        StringBuilder seg = new StringBuilder();
	        newFoldLevel = foldLevel;
	        if(newFoldLevel != lineCount)
	        {
	          if(logl > 1)
	            System.out.println(" fold level changed");
	          changed = true;
	          // Update preceding fold levels if necessary
	          if (i == firstInvalidFoldLevel)
	          {
	            Object foldHandler;
				List<Integer> precedingFoldLevels =new ArrayList<>();
	            if (precedingFoldLevels != null)
	            {
	              int j = i;
	              for (Integer foldLevel: precedingFoldLevels)
	              {
	                j--;
	                lineCount = foldLevel.intValue();
	              }
	              if (j < firstUpdatedFoldLevel)
	                firstUpdatedFoldLevel = j;
	            }
	          }
	        }
	        foldLevel = newFoldLevel;
	      }
	      if(line == foldLevel - 1)
	    	  lineCount = -1;
	      else
	    	  lineCount = line + 1;
	      if(changed)
	      {
	        if(logl > 1)
	          System.out.println("fold level changed: " + firstUpdatedFoldLevel + ',' + line);
	        fireFoldLevelChanged(firstUpdatedFoldLevel,line);
	      }
	      return newFoldLevel;
	    }
	  }

	private void fireFoldLevelChanged(int firstUpdatedFoldLevel, int line) {
		// TODO Auto-generated method stub
		
	}

}
