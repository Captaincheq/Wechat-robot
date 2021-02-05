package ltd.kuayuetech.kymonitor;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import static android.os.SystemClock.sleep;

public class MyAccessibilityService extends AccessibilityService{
    AccessibilityNodeInfo rootNode;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String nowPackageName = event.getPackageName().toString();
        if (nowPackageName.equals("com.tencent.mm") && MyApplication.getInstance()
                .getFlag()) {
            if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
                System.out.println("窗口状态变化");
                rootNode = this.getRootInActiveWindow();
                executeOperation(rootNode);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void executeOperation(AccessibilityNodeInfo info) {
        if (info == null) return;
        if (info.getChildCount() == 0) {
            if (info.getText() != null) {
                if ("Add Contacts".equals(info.getText().toString())) {
                    if ("android.widget.TextView".contentEquals(info.getClassName())) {
                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                System.out.println("点击添加朋友");
                                sleep(600);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }else if ("New Friends".equals(info.getText().toString())) {
                    if ("android.widget.TextView".contentEquals(info.getClassName())) {
                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                System.out.println("点击新的朋友");
                                sleep(600);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }else if ("Contacts".equals(info.getText().toString())) {
                    if ("android.widget.TextView".contentEquals(info.getClassName())) {
                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                System.out.println("点击通讯录");
                                sleep(600);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if (info.getChild(i) != null) {
                    executeOperation(info.getChild(i));
                }
            }
        }
    }

    @Override
    public void onInterrupt() {}
}
