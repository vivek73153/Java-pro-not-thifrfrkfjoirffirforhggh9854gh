package com.df.seller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.df.src.R;
import com.ijoomer.common.classes.IjoomerUtilities;
import com.ijoomer.customviews.IjoomerButton;
import com.smart.framework.CustomAlertNeutral;

import java.io.File;

public class DFSAddDishesImageActivity extends DFSellerMasterActivity {

	private IjoomerButton imgNext;
	private ImageView imgDish1;
    private ImageView imgDish2;
    private ImageView imgAddPhoto1;
    private ImageView imgAddPhoto2;

	final private int PICK_IMAGE_1 = 1;
	final private int PICK_IMAGE_2 = 2;

	private String selectedImagePath1 = "";
    private String selectedImagePath2 = "";

    private AQuery aQuery;

	@Override
	public int setLayoutId() {
		return R.layout.dfs_add_dish_image;
	}

	@Override
	public View setLayoutView() {
		return null;
	}

	@Override
	public void initComponents() {
        imgNext = (IjoomerButton) findViewById(R.id.imgNext);
        imgDish1 = (ImageView) findViewById(R.id.imgDish1);
        imgDish2 = (ImageView) findViewById(R.id.imgDish2);
        imgAddPhoto1 = (ImageView) findViewById(R.id.imgAddPhoto1);
        imgAddPhoto2 = (ImageView) findViewById(R.id.imgAddPhoto2);
        aQuery = new AQuery(this);
	}

	@Override
	public void prepareViews() {

	}

	@Override
	public void setActionListeners() {

        imgAddPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DFSAddDishesImageActivity.this,DFSAddDishesSetp2Activity.class);
                startActivityForResult(intent,PICK_IMAGE_1);
            }
        });

        imgAddPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DFSAddDishesImageActivity.this,DFSAddDishesSetp2Activity.class);
                startActivityForResult(intent,PICK_IMAGE_2);
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedImagePath1.length()>0 || selectedImagePath2.length()>0){
                 returnDataToCallingActivity();
                }else{
                    IjoomerUtilities.getDFInfoDialog(DFSAddDishesImageActivity.this, getString(R.string.df_dish_image_required), getString(R.string.ok), new CustomAlertNeutral() {

                        @Override
                        public void NeutralMethod() {

                        }
                    });
                }
            }
        });

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_CANCELED) {
			if (requestCode == PICK_IMAGE_1) {
				selectedImagePath1 = data.getStringExtra("IN_IMAGE_PATH");
                aQuery.id(imgDish1).image(new File(selectedImagePath1),getDeviceWidth());
			} else if (requestCode == PICK_IMAGE_2) {
				selectedImagePath2 = data.getStringExtra("IN_IMAGE_PATH");
                aQuery.id(imgDish2).image(new File(selectedImagePath2),getDeviceWidth());
			} else {
				super.onActivityResult(requestCode, resultCode, data);
			}
		}

	}

	/**
	 * Class Methods
	 */

	private void returnDataToCallingActivity() {
		Intent i = new Intent();
		i.putExtra("IN_IMAGE_PATH1", selectedImagePath1);
        i.putExtra("IN_IMAGE_PATH2", selectedImagePath2);
		setResult(Activity.RESULT_OK, i);
		finish();
	}

}
