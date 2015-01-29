package com.salesforce.androidsosguides;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 */
public class ProfileActivity extends SubActivity {

  public static final String EXTRA_CONTACT = "contact";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    Contact contact = (Contact) getIntent().getExtras().get(EXTRA_CONTACT);

    TextView name = (TextView) findViewById(R.id.contact_name);
    name.setText(contact.name);
    TextView company = (TextView) findViewById(R.id.company_name);
    company.setText(contact.company);

    GridView gridViewA = (GridView) findViewById(R.id.thumbnail_grid_a);
    gridViewA.setAdapter(new ProfileThumbnailAdapter(new int[]{
        R.drawable.profile_thumbnail_001,
        R.drawable.profile_thumbnail_002,
        R.drawable.profile_thumbnail_003,
        R.drawable.profile_thumbnail_004,
        R.drawable.profile_thumbnail_005,
        R.drawable.profile_thumbnail_006
    }));
    gridViewA.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(gridViewA));

    GridView gridViewB = (GridView) findViewById(R.id.thumbnail_grid_b);
    gridViewB.setAdapter(new ProfileThumbnailAdapter(new int[]{
        R.drawable.profile_thumbnail_007,
        R.drawable.profile_thumbnail_008,
        R.drawable.profile_thumbnail_009,
        R.drawable.profile_thumbnail_010,
        R.drawable.profile_thumbnail_011,
        R.drawable.profile_thumbnail_012,
        R.drawable.profile_thumbnail_013,
        R.drawable.profile_thumbnail_014,
        R.drawable.profile_thumbnail_015
    }));
    gridViewB.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(gridViewB));

    GridView gridViewC = (GridView) findViewById(R.id.thumbnail_grid_c);
    gridViewC.setAdapter(new ProfileThumbnailAdapter(new int[]{
        R.drawable.profile_thumbnail_016,
        R.drawable.profile_thumbnail_017,
        R.drawable.profile_thumbnail_018
    }));
    gridViewC.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(gridViewC));
  }

  private class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private GridView view;
    private boolean isResized;

    public LayoutListener(GridView view) {
      this.view = view;
    }

    @Override
    public void onGlobalLayout() {
      if (!isResized) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (view.getHeight() * (view.getAdapter().getCount() / view.getNumColumns())) - view.getPaddingTop() - view.getPaddingBottom();
        view.setLayoutParams(params);
        isResized = true;
      }
    }
  }
}
