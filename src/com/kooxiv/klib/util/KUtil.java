/**
 * 
 */
package com.kooxiv.klib.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 描述：
 * 
 * <br>
 * 
 * @author kooxiv@qq.com <br>
 *         2013-6-20 <br>
 */
public class KUtil {

	public static void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
