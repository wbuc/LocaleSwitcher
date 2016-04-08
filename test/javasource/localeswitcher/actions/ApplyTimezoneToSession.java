// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package localeswitcher.actions;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;

/**
 * 
 */
public class ApplyTimezoneToSession extends CustomJavaAction<Boolean>
{
	private IMendixObject __NewTimezone;
	private system.proxies.TimeZone NewTimezone;

	public ApplyTimezoneToSession(IContext context, IMendixObject NewTimezone)
	{
		super(context);
		this.__NewTimezone = NewTimezone;
	}

	@Override
	public Boolean executeAction() throws Exception
	{
		this.NewTimezone = __NewTimezone == null ? null : system.proxies.TimeZone.initialize(getContext(), __NewTimezone);

		// BEGIN USER CODE
		int offset = this.NewTimezone.getRawOffset();
		
		offset = offset/60000;
//		Double offsetInHrs = offset / 60.0;
//		
//		if( offsetInHrs != (offset/3600)  ) {
//			Core.getLogger(this.toString()).warn("We have less than a whole hour offset, rounding because we have no other option : " + offsetInHrs);
//		}
//		offset = offsetInHrs.intValue();		

		//We have to use the JavaScript notation, apparently this is the opposite offset in minutes
		//In otherwords an offset of -5:00 hrs for (Eastern us) needs to be provided as -300
		this.getContext().getSession().setTimeZone( offset * -1 );
		
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "ApplyTimezoneToSession";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
