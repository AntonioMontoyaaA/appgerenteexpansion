package expansion.neto.com.mx.gerenteapp.ui.agenda;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityCalendarWeekBinding;

/**
 * This is a base activity which contains week view and all the codes necessary to initialize the
 * week view.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public abstract class ActivityCalendarioSemana extends AppCompatActivity
        implements WeekView.EventClickListener,
        MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private ActivityCalendarWeekBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_week);

        initDataBinding();
        binding.weekView.setOnEventClickListener(this);
        binding.weekView.setMonthChangeListener(this);
        binding.weekView.setEventLongPressListener(this);
        binding.weekView.setEmptyViewLongPressListener(this);
        setupDateTimeInterpreter(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_semana);
        switch (id){
            case R.id.accion_calendario:
                Intent main = new Intent(ActivityCalendarioSemana.this, ActivityCalendario.class);
                ActivityCalendarioSemana.this.startActivity(main);
                ActivityCalendarioSemana.this.finish();
                return true;
            case R.id.action_hoy_ir:
                binding.weekView.goToToday();
                return true;
            case R.id.accion_dia_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    binding.weekView.setNumberOfVisibleDays(1);
                    // Lets change some dimensions to best fit the view.
                    binding.weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    binding.weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    binding.weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_semana:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    binding.weekView.setNumberOfVisibleDays(7);
                    // Lets change some dimensions to best fit the view.
                    binding.weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    binding.weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    binding.weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        binding.weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return binding.weekView;
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar_week);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_perm_contact_calendar_black_24dp);
        binding.toolbar.toolbar.setOverflowIcon(drawable);
        setSupportActionBar(binding.toolbar.toolbar);
        binding.toolbar.semana.setVisibility(View.GONE);
        binding.toolbar.nombreTitulo.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent main;
        main = new Intent(ActivityCalendarioSemana.this, ActivityCalendario.class);
        ActivityCalendarioSemana.this.startActivity(main);
        ActivityCalendarioSemana.this.finish();
    }


}
