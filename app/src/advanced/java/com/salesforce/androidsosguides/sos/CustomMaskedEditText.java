package com.salesforce.androidsosguides.sos;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.salesforce.androidsosguides.R;

/**
 * This is an example of a customization of an existing maskable field. By extending the
 * onDraw method, we can leverage the default drawing of the background Drawable as well as
 * augment it with some text. The onDraw method will be called each time tha the field is
 * updated, whether it is masked or not.
 */
public class CustomMaskedEditText extends com.salesforce.android.sos.maskview.EditText {

  public CustomMaskedEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public CustomMaskedEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomMaskedEditText(Context context) {
    super(context);
  }

  /**
   * Extends the default masked drawing behaviour to overlay some text on top of the associated
   * Drawable. Because this method is called both when the field is masked and when it is visible,
   * the text is only drawn when the field is masked.
   *
   * @{inheritDoc}
   */
  @Override
  protected void onDraw(Canvas canvas) {
    // Draw the Drawable that is associated with this field.
    super.onDraw(canvas);

    // When the field is masked, overlay some semi-transparent black text centered on the Drawable.
    if (isMasked()) {
      Paint textPaint = new Paint();
      textPaint.setARGB(63, 0, 0, 0);
      textPaint.setTextAlign(Paint.Align.CENTER);
      textPaint.setTextSize(200);

      int xPos = (canvas.getWidth() / 2);
      int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));

      canvas.drawText(getResources().getString(R.string.masked_field_label), xPos, yPos, textPaint);
    }
  }
}