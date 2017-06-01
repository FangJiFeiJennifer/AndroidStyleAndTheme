package com.keegan.kstyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }

    public void toShape(View view) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Log.d("MainActivity", "toShape: density = " + dm.density);
        Log.d("MainActivity", "toShape: densityDpi = " + dm.densityDpi);
        startActivity(new Intent(this, ShapeActivity.class));
    }

    public void toSelector(View view) {
        startActivity(new Intent(this, SelectorActivity.class));
    }

    public void toLayerList(View view) {
        startActivity(new Intent(this, LayerListActivity.class));
    }

    public void toAnimation(View view) {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    public void toImageView(View view) {
        startActivity(new Intent(this,ImageViewActivity.class));
    }

    public void toMaterialDesign(View view) {
        startActivity(new Intent(this,MaterialDesignActivity.class));
    }

    public void toLayoutWeight(View view) {
        startActivity(new Intent(this,LayoutWeightActivity.class));
    }
}
