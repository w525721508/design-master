package design.root.base.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import design.root.base.R;


/**
 * Created by tzduan on 18/2/1.
 */

public class TitleView extends RelativeLayout {
    private TextView mTitleText;
    private ImageView mLeftImage, mRightImage;
    private OnClickListener mLeftClickListener, mRightClickListener;

    private boolean isLeftButtonBack = true;
    private boolean isShowLeftButton = true;
    private boolean isShowRightButton;
    private String mTitleString;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
        initListener();
    }

    private void initListener() {
        mLeftImage.setOnClickListener(v -> {
            if (mLeftClickListener != null){
                mLeftClickListener.onClick(v);
            }
            if (isLeftButtonBack){
                sendKeyBackEvent();
            }
        });
        mRightImage.setOnClickListener(v -> {
            if (mRightClickListener != null){
                mRightClickListener.onClick(v);
            }
        });
    }

    private void setLeftClickListener(OnClickListener clickListener){
        mLeftClickListener = clickListener;
    }

    private void setRightClickListener(OnClickListener clickListener){
        mRightClickListener = clickListener;    
    }

    private void setTitle(String title){
        mTitleText.setText(title);
    }

    private void initViews(Context context, AttributeSet attrs) {
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
            mTitleString = typedArray.getString(R.styleable.TitleView_title_text);
            isLeftButtonBack = typedArray.getBoolean(R.styleable.TitleView_title_leftbutton_back, true);
            isShowLeftButton = typedArray.getBoolean(R.styleable.TitleView_title_leftbutton_show, true);
            isShowRightButton = typedArray.getBoolean(R.styleable.TitleView_title_rightbutton_show, true);
        }
        View rootView = LayoutInflater.from(context).inflate(R.layout.title_view, this);
        mTitleText = rootView.findViewById(R.id.title_text);
        mLeftImage = rootView.findViewById(R.id.left_button);
        mRightImage = rootView.findViewById(R.id.right_button);

        if (!TextUtils.isEmpty(mTitleString)){
            mTitleText.setText(mTitleString);
        }

        mLeftImage.setVisibility(isShowLeftButton ? VISIBLE : INVISIBLE);
        mRightImage.setVisibility(isShowRightButton ? VISIBLE : INVISIBLE);

    }

    private void sendKeyBackEvent() {
        Context context = this.getContext();
        if(context instanceof Activity) {
//            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
//            ((Activity)context).getWindow().getDecorView().dispatchKeyEvent(keyEvent);
            ((Activity) context).onBackPressed();
        }

    }

    public void setShowLeftButton(boolean showLeftButton) {
        isShowLeftButton = showLeftButton;
    }

    public void setShowRightButton(boolean showRightButton) {
        isShowRightButton = showRightButton;
    }

    public void setIsLeftButtonBack(boolean isLeftButtonBack) {
        this.isLeftButtonBack = isLeftButtonBack;
    }
}
