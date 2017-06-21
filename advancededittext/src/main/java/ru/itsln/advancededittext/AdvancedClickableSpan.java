package ru.itsln.advancededittext;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;
import android.view.View;

/**
 * Created by smb on 21/05/2017.
 */

public class AdvancedClickableSpan extends ReplacementSpan{

    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, @IntRange(from = 0) int start, @IntRange(from = 0) int end, @Nullable Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, @IntRange(from = 0) int start, @IntRange(from = 0) int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        RectF rect = new RectF(x, top, x + paint.measureText(text, start, end), bottom);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, x, y, paint);

    }

    public void onClick(View widget) {
        if (onClickListener != null) {onClickListener.onClick(widget);}
    }

    public void onLongClick(View widget){
        if (onLongClickListener != null) {onLongClickListener.onLongClick(widget);}
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}
