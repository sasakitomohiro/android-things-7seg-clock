package t0mat0ts.com.androidthings7segclock;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {
    PeripheralManager manager;
    Gpio a,f, b, g, c, dp, d, e, seg1, seg2, seg3;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initGpio();
        } catch (IOException e) {
        }

//        Handler handler  = new Handler(Looper.getMainLooper());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    show(1, 1);
//
//                } catch (IOException e) {
//
//                }
//            }
//        });
        Timer timer =  new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    show(10, 10);
                    if (count < 4) {
                        show(count, count);
//                        Log.e("aaa", String.valueOf(count));
                        count++;
                    } else {
                        count = 1;
                    }
                } catch (IOException e) {

                }
            }
        }, 1, 5);
    }

    @Override
    protected void onDestroy() {
        try {
            a.close();
            b.close();
            c.close();
            d.close();
            e.close();
            f.close();
            g.close();
            dp.close();
            seg1.close();
            seg2.close();
            seg3.close();
        } catch (IOException e) {

        }
        super.onDestroy();
    }

    public void show(int number, int segment) throws IOException {
        selectNumber(number);
        selectSegment(segment);
    }

    public void selectNumber(int number) throws IOException {
        switch (number) {
            case 0:
                a.setValue(false);
                b.setValue(false);
                c.setValue(false);
                d.setValue(false);
                e.setValue(false);
                f.setValue(false);
                g.setValue(true);
                dp.setValue(true);
                break;
            case 1:
                a.setValue(true);
                b.setValue(false);
                c.setValue(false);
                d.setValue(true);
                e.setValue(true);
                f.setValue(true);
                g.setValue(true);
                dp.setValue(true);
                break;
            case 2:
                a.setValue(false);
                b.setValue(false);
                c.setValue(true);
                d.setValue(false);
                e.setValue(false);
                f.setValue(true);
                g.setValue(false);
                dp.setValue(true);
                break;
            case 3:
                a.setValue(false);
                b.setValue(false);
                c.setValue(false);
                d.setValue(false);
                e.setValue(true);
                f.setValue(true);
                g.setValue(false);
                dp.setValue(true);
                break;
            case 4:
                a.setValue(true);
                b.setValue(false);
                c.setValue(false);
                d.setValue(true);
                e.setValue(true);
                f.setValue(false);
                g.setValue(false);
                dp.setValue(true);
                break;
            case 5:
                a.setValue(false);
                b.setValue(true);
                c.setValue(false);
                d.setValue(false);
                e.setValue(true);
                f.setValue(false);
                g.setValue(false);
                dp.setValue(true);
                break;
            case 6:
                a.setValue(false);
                b.setValue(true);
                c.setValue(false);
                d.setValue(false);
                e.setValue(false);
                f.setValue(false);
                g.setValue(false);
                dp.setValue(false);
                break;
            case 7:
                a.setValue(false);
                b.setValue(false);
                c.setValue(false);
                d.setValue(true);
                e.setValue(true);
                f.setValue(true);
                g.setValue(true);
                dp.setValue(true);
                break;
            case 8:
                a.setValue(false);
                b.setValue(false);
                c.setValue(false);
                d.setValue(false);
                e.setValue(false);
                f.setValue(false);
                g.setValue(false);
                dp.setValue(true);
                break;
            case 9:
                a.setValue(false);
                b.setValue(false);
                c.setValue(false);
                d.setValue(false);
                e.setValue(true);
                f.setValue(false);
                g.setValue(false);
                dp.setValue(false);
                break;
            default:
                a.setValue(true);
                b.setValue(true);
                c.setValue(true);
                d.setValue(true);
                e.setValue(true);
                f.setValue(true);
                g.setValue(true);
                dp.setValue(true);
        }
    }

    public void selectSegment(int segment) throws IOException {
        switch (segment) {
            case 1:
                seg1.setValue(true);
                seg2.setValue(false);
                seg3.setValue(false);
                break;
            case 2:
                seg1.setValue(false);
                seg2.setValue(true);
                seg3.setValue(false);
                break;
            case 3:
                seg1.setValue(false);
                seg2.setValue(false);
                seg3.setValue(true);
                break;
            default:
                seg1.setValue(false);
                seg2.setValue(false);
                seg3.setValue(false);
        }
    }

    public void initGpio() throws IOException {
        manager = PeripheralManager.getInstance();
        a = manager.openGpio("BCM20");
        seg1 = manager.openGpio("BCM21");
        seg2 = manager.openGpio("BCM22");
        seg3 = manager.openGpio("BCM23");
        f = manager.openGpio("BCM19");
        b = manager.openGpio("BCM18");
        g = manager.openGpio("BCM17");
        c = manager.openGpio("BCM16");
        dp = manager.openGpio("BCM15");
        d = manager.openGpio("BCM14");
        e = manager.openGpio("BCM13");
        a.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        f.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        b.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        g.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        c.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        dp.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        d.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        e.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        seg1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        seg2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        seg3.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
    }
}
