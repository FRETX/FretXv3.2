/**
 * Sample code for "Making Musical Apps" by Peter Brinkmann
 * http://shop.oreilly.com/product/0636920022503.do
 */

package fretx.version3;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.puredata.android.service.PdService;

public class PitchView extends View {

    private float centerPitch, currentPitch;
    private int width, height;
    private final Paint paint = new Paint();

    public PitchView(Context context) {
        super(context);
    }

    public PitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PitchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCenterPitch(float centerPitch) {
        this.centerPitch = centerPitch;
        invalidate();
    }

    public void setCurrentPitch(float currentPitch) {
        this.currentPitch = currentPitch;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float halfWidth = width / 2;
        paint.setStrokeWidth(6.0f);
        paint.setColor(Color.GREEN);
        canvas.drawLine(halfWidth, 0, halfWidth, height, paint);

        float dx = (currentPitch - centerPitch) / 2;
        if (-1 < dx && dx < 1) {
            paint.setStrokeWidth(6.0f);
            paint.setColor(Color.BLUE);
            //successPlayer();
        } else {
            paint.setStrokeWidth(8.0f);
            paint.setColor(Color.RED);
            dx = (dx < 0) ? -1 : 1;
        }
        double phi = dx * Math.PI / 4;
        canvas.drawLine(halfWidth, height,
                halfWidth + (float)Math.sin(phi) * height * 0.9f,
                height - (float)Math.cos(phi) * height * 0.9f, paint);
    }

    public void successPlayer(){
        Context context = getContext();
        MediaPlayer mediaPlayer= MediaPlayer.create(context, R.raw.success_sound);
        mediaPlayer.start();
    }
}