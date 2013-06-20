/**
 * 
 */
package com.kooxiv.klib;

import garin.artemiy.sqlitesimple.library.SQLiteSimpleDAO;
import android.content.Context;

/**
 * 描述：
 * 
 * <br/>
 * 
 * @author kooxiv@qq.com <br/>
 *         2013-6-20 <br/>
 * @param <T>
 */
public class KDbClient<T> extends SQLiteSimpleDAO<T> {

	public KDbClient(Context context, Class<T> beanClass) {
		super(beanClass, context);
	}

	public KDbClient(Context context, Class<T> beanClass, String dbName) {
		super(beanClass, context, dbName);
	}

}
