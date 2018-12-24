
package com.mahbub.googlehomenotification;


import android.util.Log;

/**
 * A simple class for logging.
 *@author Mahbubul Islam
 */
public class Logger 
{
	/**
	 * Switch the logging on/off. Setting false will not generate code for logging.
	 * Enable show line number. If enable logger will show the line number of source from 
	 * which it was called.
	 */
	private static final boolean LOG = true;
	private static final boolean SHOW_LINE_NUMBER = true; 
	
	Object mLoggerClass;
	String mClassName;
	
	/**
	 * Constuctor of the Logger. 
	 * 
	 * @param loggerclass the class name of this object will be used as TAG for logging.
	 */
	public Logger(Object loggerclass)
	{
		this.mLoggerClass=loggerclass;
		mClassName=loggerclass.getClass().getSimpleName();
	}
	
	/**
	 * Log info type message.
	 * @param msg the information which will be logged.
	 */
	public void info(String msg)
	{
		if(LOG)
		{
			msg=msg+"";
			if(SHOW_LINE_NUMBER)
			{
				int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
				String TAG = mClassName+":"+lineNumber;
				Log.i(TAG, msg);
			}
			else
			{
				Log.i(mClassName, msg);
			}
		}
	}
	/**
	 * Log warn type message.
	 * @param msg the information which will be logged.
	 */
	public void warn(String msg)
	{
		if(LOG)
		{
			msg=msg+"";
			if(SHOW_LINE_NUMBER)
			{
				int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
				String TAG = mClassName+":"+lineNumber;
				Log.w(TAG, msg);
			}
			else
			{
				Log.w(mClassName, msg);
			}
		}
	}
	/**
	 * Log debug type message.
	 * @param msg the information which will be logged.
	 */
	public void debug(String msg)
	{
		if(LOG)
		{
			msg=msg+"";
			if(SHOW_LINE_NUMBER)
			{
				int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
				String TAG = mClassName+":"+lineNumber;
				Log.d(TAG, msg);
			}
			else
			{
				Log.d(mClassName, msg);
			}
		}
	}
	/**
	 * Log error type message.
	 * @param msg the information which will be logged.
	 */
	public void error(String msg)
	{
		if(LOG)
		{
			msg=msg+"";
			if(SHOW_LINE_NUMBER)
			{
				int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
				String TAG = mClassName+":"+lineNumber;
				Log.e(TAG, msg);
			}
			else
			{
				Log.e(mClassName, msg);
			}
		}
	}
	/**
	 * Log verbose type message.
	 * @param msg the information which will be logged.
	 */
	public void verbose(String msg)
	{
		if(LOG)
		{
			msg=msg+"";
			if(SHOW_LINE_NUMBER)
			{
				int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
				String TAG = mClassName+":"+lineNumber;
				Log.v(TAG, msg);
			}
			else
			{
				Log.v(mClassName, msg);
			}
		}
	}
	
	
}
