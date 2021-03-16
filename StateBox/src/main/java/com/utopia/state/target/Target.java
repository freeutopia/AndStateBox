package com.utopia.state.target;

import android.view.View;
import com.utopia.state.mask.MaskView;
import com.utopia.state.mask.MaskedView;

public interface Target {
    void replaceView();

    MaskView getMaskView();

    View getView();

    MaskedView getMaskedView();
}
