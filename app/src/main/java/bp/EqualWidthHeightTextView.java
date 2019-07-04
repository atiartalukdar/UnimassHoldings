package bp;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

public class EqualWidthHeightTextView extends android.support.v7.widget.AppCompatTextView {

    public EqualWidthHeightTextView(Context context) {
        super(context);
    }

    public EqualWidthHeightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EqualWidthHeightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int r = Math.max(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(r, r);

    }
}