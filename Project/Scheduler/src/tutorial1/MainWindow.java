/**
 * Copyright (c) 2015, MindFusion LLC - Bulgaria.
 */
package tutorial1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.awt.AwtCalendar;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainWindow().setVisible(true);
                } catch (Exception exp) {
                }
            }
        });
    }

    protected MainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Tutorial 1");

        calendar = new AwtCalendar();
        getContentPane().add(calendar, BorderLayout.CENTER);

        calendar.beginInit();
        calendar.setCurrentView(CalendarView.Timetable);
        calendar.getTimetableSettings().setShowDayHeader(true);
        calendar.getTimetableSettings().setVisibleColumns(3);
        calendar.getTimetableSettings().getDates().clear();
        for (int i = 0; i < 5; i++) {
            calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(i));
            //calendar.getTimetableSettings().getDates()
            calendar.getTimetableSettings().getShowDayHeader().toString();
        }
        calendar.endInit();
    }

    private static final long serialVersionUID = 1L;

    private AwtCalendar calendar;
}
