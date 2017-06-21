package ru.itsln.advancededittext;

import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ReplacementSpan;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by smb on 18/05/2017.
 */

class AdvancedTextWatcher implements TextWatcher {

    private TextView editor;
    private SpanFactory spans;
    private ArrayList<ReplacementSpan> spansToRemove = new ArrayList<>();

    AdvancedTextWatcher(TextView editor) {
        this.editor = editor;
    }

    public void insert(String template) {
        insert(template, editor.getSelectionStart(), editor.getSelectionEnd());
    }

    public void insert(String template, int start, int end){
        ReplacementSpan span = spans.getSpanByTemplate(template);
        if (span != null) {
            if (start >=0 && end>=0) {
                Editable message = editor.getEditableText();
                message.replace(start, end, template);
                message.setSpan(span, start, start + template.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (count > 0) {
            int end = start + count;
            Editable message = editor.getEditableText();
            ReplacementSpan[] list = message.getSpans(start, end, ReplacementSpan.class);
            for (ReplacementSpan span : list) {
                int spanStart = message.getSpanStart(span);
                int spanEnd = message.getSpanEnd(span);
                if ((spanStart < end) && (spanEnd > start)) {
                    spansToRemove.add(span);
                }
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        Editable message = editor.getEditableText();
        for (ReplacementSpan span : spansToRemove) {
            int start = message.getSpanStart(span);
            int end = message.getSpanEnd(span);
            message.removeSpan(span);
            if (start != end) {
                message.delete(start, end);
            }
        }
        spansToRemove.clear();
    }


    public void setSpans(SpanFactory spans) {
        this.spans = spans;
    }
}
