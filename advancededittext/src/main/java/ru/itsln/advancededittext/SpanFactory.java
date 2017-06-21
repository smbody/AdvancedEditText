package ru.itsln.advancededittext;

import android.text.style.ReplacementSpan;

/**
 * Created by smb on 19/05/2017.
 */

public interface SpanFactory {
    ReplacementSpan getSpanByTemplate(String template);
}
