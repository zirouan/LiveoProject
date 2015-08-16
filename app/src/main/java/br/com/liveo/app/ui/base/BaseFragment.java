package br.com.liveo.app.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.lang.reflect.Method;

import br.com.liveo.app.R;
import br.com.liveo.app.util.ActivityAnimation;

public class BaseFragment extends Fragment {

    public void startActivity(Intent intent, final ActivityAnimation animation) {
        startActivity(intent);
        putAnimation(getActivity(), animation);
    }

    public void startActivityForResult(Intent intent, int requestCode, final ActivityAnimation animation) {
        startActivityForResult(intent, requestCode);
        putAnimation(getActivity(), animation);
    }

    private static void putAnimation(final Activity source, final ActivityAnimation animation) {
        try {
            Method method = Activity.class.getMethod("overridePendingTransition", int.class, int.class);

            int[] animations = getAnimation(animation);
            method.invoke(source, animations[0], animations[1]);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static int[] getAnimation(final ActivityAnimation animation) {

        int exitAnim;
        int enterAnim;

        switch (animation) {
            case TRANSITION_RIGHT:
                enterAnim = R.anim.transition_right_enter;
                exitAnim = R.anim.transition_right_exit;
                break;

            case TRANSITION_LEFT:
            default:
                enterAnim = R.anim.transition_left_enter;
                exitAnim = R.anim.transition_left_exit;
                break;
        }

        return new int[]{enterAnim, exitAnim};
    }
}
