package de.elite.games.android.rpglibdemo.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import de.elite.games.android.rpglibdemo.R;
import de.elite.games.android.rpglibdemo.map.TestRpgMap;

public class MapView extends View {
    private TestRpgMap rpgMap;

    public MapView(Context context) {
        super(context);
        init(null, 0);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rpgMap != null){
            rpgMap.draw(canvas);
        }
    }

    @Override
    public boolean performClick() {
        //FIXME why override?
        return super.performClick();
    }

    public void setMap(TestRpgMap rpgMap) {
        this.rpgMap = rpgMap;
    }
}
