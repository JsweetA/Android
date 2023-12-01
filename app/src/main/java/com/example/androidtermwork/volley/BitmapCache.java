package com.example.androidtermwork.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapCache implements ImageLoader.ImageCache {
 
	private LruCache<String, Bitmap> mCache;
 
	public BitmapCache() {
		int maxSize = (int) Runtime.getRuntime().maxMemory();//获取手机分配给应用的最大内存
		mCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}
 
	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}
 
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
	}
 
}