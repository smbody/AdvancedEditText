package ru.itsln.advancededittext;

import android.os.Handler;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by smb on 20/05/2017.
 */

public class AdvancedMovementMethod extends LinkMovementMethod{

    private static final long LONG_CLICK_TIME = 1000;
    private Handler longClickHandler = new Handler();
    private boolean longPressed;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if(action == MotionEvent.ACTION_CANCEL){
            longClickHandler.removeCallbacksAndMessages(null);}

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();
            x += widget.getScrollX();
            y += widget.getScrollY();
            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            final AdvancedClickableSpan[] link = buffer.getSpans(off, off, AdvancedClickableSpan.class);

            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    longClickHandler.removeCallbacksAndMessages(null);
                    if (!longPressed) { link[0].onClick(widget);}
                    longPressed = false;
                } else {
                    Selection.setSelection(buffer,buffer.getSpanStart(link[0]),buffer.getSpanEnd(link[0]));
                    longClickHandler.postDelayed(getLongDelay(widget, link[0]), LONG_CLICK_TIME);
                }
                return true;
            }
        }

        return super.onTouchEvent(widget, buffer, event);
    }

    public Runnable getLongDelay(final TextView widget, final AdvancedClickableSpan span) {
        return new Runnable() {
            @Override
            public void run() {
                span.onLongClick(widget);
                longPressed = true;
            }
        };
    }

    private static AdvancedMovementMethod instance;

    public static MovementMethod getInstance() {
        if (instance == null) { instance = new AdvancedMovementMethod(); }
        return instance;
    }


}
