package ru.itsln.smilesdemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.widget.Toast;

import ru.itsln.advancededittext.*;

/**
 * Created by smb on 19/05/2017.
 */

public class SmilesSpanFactory implements SpanFactory {

    private Resources resources;
    private Context context;

    public SmilesSpanFactory(Context context) {
        this.resources = context.getResources();
        this.context = context;
    }

    @Override
    public ReplacementSpan getSpanByTemplate(String template) {
        switch (template) {
            case ":-)": return getSpanImage(R.drawable.ic_smile);
            case ":-(": return getSpanImage(R.drawable.ic_sad_smile);
            case ":clickable:": return getClickableImageSpan();
            default: return null;

        }
    }

    private ReplacementSpan getSpanImage(int resource) {
        Drawable drawable = resources.getDrawable(resource);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan image = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        return image;
    }

    public ReplacementSpan getClickableImageSpan() {
        AdvancedClickableSpan span = new AdvancedClickableSpan();
        span.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Function click!", Toast.LENGTH_SHORT).show();
            }
        });
        span.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Function long click!", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return span;
    }
}
