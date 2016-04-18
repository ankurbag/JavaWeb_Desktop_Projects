/**
 * Copyright (c) 2015, MindFusion LLC - Bulgaria.
 */

package tutorial2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends JFrame
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new MainWindow().setVisible(true);
				}
				catch (Exception exp)
				{
				}
			}
		});
	}

	protected MainWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("Tutorial 2");

		calendar = new AwtCalendar();
		getContentPane().add(calendar, BorderLayout.CENTER);

		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.setDate(new DateTime(2015, 1, 1));
		calendar.setEndDate(new DateTime(2015, 3, 1));

		Appointment app = new Appointment();
		app.setHeaderText("Meet George");
		app.setDescriptionText("This is a sample appointment");
		app.setStartTime(new DateTime(2015, 1, 10, 14, 0, 0));
		app.setEndTime(new DateTime(2015, 1, 10, 16, 30, 0));
		calendar.getSchedule().getItems().add(app);

		Recurrence rec = new Recurrence();
		rec.setPattern(RecurrencePattern.Weekly);
		rec.setDaysOfWeek(DaysOfWeek.Monday | DaysOfWeek.Wednesday);
		rec.setWeeks(2);
		rec.setStartDate(new DateTime(2006, 1, 10));
		rec.setRecurrenceEnd(RecurrenceEnd.Never);
		app.setRecurrence(rec);

		calendar.endInit();
	}


	private static final long serialVersionUID = 1L;

	private AwtCalendar calendar;
}
