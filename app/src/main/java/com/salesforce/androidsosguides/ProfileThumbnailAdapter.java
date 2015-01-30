package com.salesforce.androidsosguides;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 *
 */
public class ProfileThumbnailAdapter extends BaseAdapter {

  private int[] drawableIds;

  public ProfileThumbnailAdapter(int ... drawableIds) {
    this.drawableIds = drawableIds;
  }

  @Override
  public int getCount() {
    return drawableIds.length;
  }

  @Override
  public long getItemId(int position) {
    return drawableIds[position];
  }

  @Override
  public Object getItem(int position) {
    return drawableIds[position];
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {
      imageView = new ImageView(parent.getContext());
      int thumbnailSize = getColumnWidth((GridView) parent);
      imageView.setLayoutParams(new GridView.LayoutParams(thumbnailSize, thumbnailSize));
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    } else {
      imageView = (ImageView) convertView;
    }

    Bitmap image = BitmapFactory.decodeResource(parent.getResources(), (int) getItem(position));
    imageView.setImageBitmap(image);
    return imageView;
  }

  private int getColumnWidth(GridView view) {
    if (android.os.Build.VERSION.SDK_INT >= 16) {
      return view.getColumnWidth();
    }

    return (view.getWidth() - view.getPaddingLeft() - view.getPaddingRight()) / view.getNumColumns();
  }
}
