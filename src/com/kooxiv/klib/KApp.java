/**
 * 
 */
package com.kooxiv.klib;

import android.app.Application;

/**
 * 描述：
 * 
 * <br>
 * 
 * @author kooxiv@qq.com <br>
 *         2013-6-20 <br/>
 */
public abstract class KApp extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		creatDbTables();
	}

	/**
	 * 创建数据库 <br>
	 * SQLiteSimple databaseSimple = new SQLiteSimple(this); <br>
	 * databaseSimple.create(Record.class);
	 */
	protected abstract void creatDbTables();

}
