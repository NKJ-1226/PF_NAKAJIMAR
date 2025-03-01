package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 */
public class AppUtil {
	
	/**
	 * メッセージIDからメッセージを取得する。
	 * 
	 *  @param MessageSource メッセージソース
	 *  @param key メッセージキー
	 *  @param params 置換文字群
	 *  @return メッセージ
	 *  */
	public static String getMessage(MessageSource messageSource, String key,Object...params) {
		return messageSource.getMessage(key, params,Locale.JAPAN);
	}

}
