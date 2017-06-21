package ru.itsln.advancededittext;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


/**
 * Created by smb on 18/05/2017.
 */

public class AdvancedEditText extends AppCompatEditText {

    private AdvancedTextWatcher watcher;

    public AdvancedEditText(Context context) {
        this(context, null);
    }

    public AdvancedEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public AdvancedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        watcher = new AdvancedTextWatcher(this);
        addTextChangedListener(watcher);
        setMovementMethod(AdvancedMovementMethod.getInstance());
    }

    public void insert(String template) {
        watcher.insert(template);
    }

    public void setSpans(SpanFactory spans) {
        watcher.setSpans(spans);
    }
}
