/**
 * Copyright (c) 2015, MindFusion LLC - Bulgaria.
 */

package googleCalendar;

import java.awt.event.*;
import java.lang.reflect.*;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.drawing.awt.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.awt.*;
import com.mindfusion.scheduling.model.*;
import com.mindfusion.scheduling.model.ItemEvent;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(706, 497);
		setTitle("MindFusion.Scheduling Sample: Google-themed Calendar");
		setInfo("This sample illustrates how to use the visualization settings and custom " +
			"drawing to achieve a specific appearance; in this particular case - a calendar " +
			"with a theme similar to Google's calendar.");
		
		calendar = new GoogleCalendar();

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setEnableDragCreate(true);
		
		calendar.getTimetableSettings().getTimelineStyle().setHeaderBrush(new SolidBrush(headerFillColor));
		calendar.getTimetableSettings().getTimelineStyle().setHeaderTextColor(Colors.Transparent);
		calendar.getTimetableSettings().getTimelineStyle().setLineColor(Colors.Transparent);
		calendar.getTimetableSettings().getTimelineStyle().setTextColor(Colors.Transparent);

		calendar.getTimetableSettings().getStyle().setHeaderTextColor(headerTextColor);
		calendar.getTimetableSettings().getStyle().setHeaderFont(headerFont);
		calendar.getTimetableSettings().getStyle().setHeaderBrush(new SolidBrush(headerFillColor));

		calendar.getTimetableSettings().getCellStyle().setBorderLeftColor(lineColor);
		calendar.getTimetableSettings().getCellStyle().setBorderTopColor(Colors.Transparent);
		calendar.getTimetableSettings().getCellStyle().setBorderRightColor(lineColor);
		calendar.getTimetableSettings().getCellStyle().setBorderBottomColor(Colors.Transparent); // this will be custom-drawn depending on cell
		calendar.getTimetableSettings().getCellStyle().setLineColor(lineColor);

		calendar.getTimetableSettings().setHeaderDateFormat("EEE MM/dd");
		calendar.getTimetableSettings().setMainHeaderSize(18);
		calendar.getTimetableSettings().setInfoHeaderSize(7);
		calendar.getTimetableSettings().setInfoHeaderBrush(new SolidBrush(headerFillColor));
		calendar.getTimetableSettings().setShowWorkTime(false);
		calendar.getTimetableSettings().setShowDayHeader(true);
		calendar.getTimetableSettings().setShowInfoHeader(true);
		calendar.getTimetableSettings().setColumnBandSize(0);
		calendar.getTimetableSettings().setVisibleColumns(5);
		
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 6));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 25));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 26));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 27));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 28));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 6, 29));
		calendar.getTimetableSettings().getDates().add(new DateTime(2012, 5, 6));
		
		calendar.setCustomDraw(
			CustomDrawElements.TimetableTimelineHourCell |
			CustomDrawElements.TimetableWholeDayHeader |
			CustomDrawElements.TimetableCell |
			CustomDrawElements.TimetableColumnHeader |
			CustomDrawElements.TimetableInfoHeader);

		Style personalEvents = new Style();
		personalEvents.setTextColor(new Color(0xFF, 0x80, 0x80, 0x80));
		personalEvents.setBorderTopColor(Colors.Goldenrod);
		personalEvents.setBorderLeftColor(Colors.Goldenrod);
		personalEvents.setBorderBottomColor(Colors.Goldenrod);
		personalEvents.setBorderRightColor(Colors.Goldenrod);
		personalEvents.setLineColor(Colors.Goldenrod);
		personalEvents.setFillColor(new Color(250, 200, 100));
		personalEvents.setBrush(new GradientBrush(Colors.White, Colors.PaleGoldenrod, 90));
		personalEvents.setHeaderTextColor(Colors.DarkGoldenrod);
		calendar.getItemSettings().setStyle(personalEvents);
		calendar.getItemSettings().setSelectedItemStyle(personalEvents);

		calendar.setShowToolTips(true);

		calendar.getItemSettings().setMoveBandSize(0);
		calendar.getItemSettings().setResizeBandSize(0);
		// TODO:
		//calendar.setAllowInplaceCreate(false);

		calendar.getSelection().setStyle(SelectionStyle.None);
		calendar.getSelection().getSelectedElementsStyle().setBrush(new SolidBrush(new Color(20, Colors.Black)));
		calendar.endInit();
		// Calendar initialization end

		calendar.addCalendarListener(new CalendarAdapter() {
			@Override public void draw(DrawEvent e) {
				onCalendarDraw(e);
			}
			@Override public void hiddenItemClick(DateEvent e) {
				onCalendarHiddenItemClick(e);
			}

			// Disable all interactions while the info box is open
			@Override
			public void itemModifying(ItemModifyConfirmEvent e) {
				onCalendarItemModifying(e);
			}
			@Override
			public void itemInplaceEditStarting(ItemConfirmEvent e) {
				onCalendarItemInplaceEditStarting(e);
			}
			@Override
			public void itemSelecting(ItemConfirmEvent e) {
				onCalendarItemSelecting(e);
			}
			
			@Override
			public void itemTooltipDisplaying(ItemTooltipEvent e) {
				onCalendarItemTooltipDisplaying(e);
			}

			@Override
			public void itemCreated(ItemEvent e) {
				onItemCreated(e);
			}
		});
		
		calendar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1)
					onCalendarClicked(e);
				else if (e.getClickCount() == 2)
					onCalendarDoubleClicked(e);
			}
			public void mouseMoved(java.awt.event.MouseEvent e) {
				onCalendarMouseMoved(e);
			}
		});
		
		calendar.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				onCalendarSizeChanged(e);
			}
		});

		content.add(calendar);

		JMenuBar menu = getJMenuBar();
		if (menu != null)
		{
			JMenu menuFile = menu.getMenu(0);

			menuFile.addSeparator();

			JMenuItem menuPrintPreview = new JMenuItem("Print Preview");
			menuPrintPreview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calendar.printPreview();
				}
			});
			menuFile.add(menuPrintPreview);

			JMenuItem menuPrint = new JMenuItem("Print");
			menuPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calendar.print();
				}
			});
			menuFile.add(menuPrint);
		}
	}

	private void onCalendarMouseMoved(MouseEvent e) {
		if (calendar.getItemAt(e.getX(), e.getY()) != null)
			calendar.setCursor(Cursor.Hand);
		else
			calendar.setCursor(Cursor.Default);
	}

	protected void onCalendarItemTooltipDisplaying(ItemTooltipEvent e) {
		if (e.getItem().getAllDayEvent())
			e.setTooltip(e.getItem().getHeaderText() + "\n" + e.getItem().getDescriptionText());
		else
			e.setTooltip("");
	}

	private void onItemCreated(ItemEvent e) {
//		calendar.startInplaceEdit(e.getItem());
	}

	private void onCalendarItemSelecting(ItemConfirmEvent e) {
		if (infoColumn != -1)
			e.setConfirm(false);
	}

	private void onCalendarItemInplaceEditStarting(ItemConfirmEvent e) {
		if (infoColumn != -1)
			e.setConfirm(false);
	}

	private void onCalendarItemModifying(ItemModifyConfirmEvent e) {
		if (infoColumn != -1)
			e.setConfirm(false);
	}

	private void onCalendarDoubleClicked(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (int i = 0; i < calendar.getTimetableSettings().getDates().size(); i++)
		{
			Rectangle r = calendar.getElementBounds(CalendarElement.TimetableDayHeader, i);
			if (r.contains(p))
			{
				Appointment app = new Appointment();
				app.setStartTime(calendar.getTimetableSettings().getDates().get(i));
				app.setEndTime(calendar.getTimetableSettings().getDates().get(i).addDays(1));
				app.setAllDayEvent(true);
				calendar.getSchedule().getItems().add(app);
			}
		}
	}

	private void onCalendarHiddenItemClick(DateEvent e) {
		// Find the index of the column
		int index = 0;
		for (DateTime date : calendar.getTimetableSettings().getDates())
		{
			if (date.equals(e.getDate()))
				break;

			index++;
		}

		// If the same column is already displayed, ignore the click
		if (infoColumn == index)
			return;

		Rectangle headerBounds = calendar.getElementBounds(CalendarElement.TimetableColumnHeader, index);
		// Expand the rectangle to the bottom of the view
		int headerBoundsH = calendar.getHeight() - headerBounds.getY() - 2;
		int headerBoundsY = headerBounds.getY() + calendar.getTimetableSettings().getMainHeaderSize();
		headerBoundsH -= calendar.getTimetableSettings().getMainHeaderSize();
		
		currentColumnBounds = new Rectangle(headerBounds.getX(), headerBoundsY, headerBounds.getWidth(), headerBoundsH);
		infoColumn = index;
		calendar.getSelection().setEnabled(false);
		calendar.getItemSettings().setShowMoreItemsCue(false);

		// Get all items in the column
		allItems = calendar.getSchedule().getAllItems(e.getDate(), e.getDate().addDays(1));

		calendar.invalidate();

		ignoreNextClick = true;
	}

	private void onCalendarPaintComponent(java.awt.Graphics e) {
		// Creating a cascading style over Calendar.ItemSettings.Style.
		// All properties that are not explicitly set will propagate to
		// the parent style. Note, that the used constructor is not visible
		// because it was not meant to be used outside of the control.
		Style personalEvents = new Style(calendar.getItemSettings().getStyle());
		
		// On the other hand you can simply use Calendar.ItemSettings.Style
		//Style personalEvents = calendar1.ItemSettings.Style;

		personalEvents.setTextColor(new Color(0xFF, 0x80, 0x80, 0x80));
		personalEvents.setBorderTopColor(Colors.Goldenrod);
		personalEvents.setBorderLeftColor(Colors.Goldenrod);
		personalEvents.setBorderBottomColor(Colors.Goldenrod);
		personalEvents.setBorderRightColor(Colors.Goldenrod);
		personalEvents.setLineColor(Colors.Goldenrod);
		personalEvents.setFillColor(new Color(250, 200, 100));
		personalEvents.setBrush(new GradientBrush(Colors.White, Colors.PaleGoldenrod, 90));
		personalEvents.setHeaderTextColor(Colors.DarkGoldenrod);

		if (currentColumnBounds != null && !currentColumnBounds.isEmpty())
		{
			com.mindfusion.drawing.Graphics g = new AwtGraphics((java.awt.Graphics2D)e);
			Brush brush = new SolidBrush(new Color(225, Colors.White));
			g.fillRectangle(brush, currentColumnBounds);
			brush.dispose();
			g.drawRectangle(Pens.LightSteelBlue, currentColumnBounds);

			Point p = currentColumnBounds.getLocation();
			for (Item item : allItems)
			{
				// Instantiating from the ItemDrawContext class - it is done
				// through reflection because this class is not meant to be
				// instantiated directly.
				Constructor<?>[] constructors = ItemDrawContext.class.getConstructors();
				//	BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Instance);

				int padding = 2;
				ItemDrawContext context = null;
				try {
					context = (ItemDrawContext)constructors[0].newInstance(
						calendar,
						item,
						g,
						new Rectangle(p.getX() + padding, p.getY() + padding, currentColumnBounds.getWidth() - 2 * padding, calendar.getItemSettings().getSize()),
						personalEvents,
						true, // Is all-day event ?
						true, // Is horizontal ?
						true, // Starts here ?
						true, // Ends here ?
						false, // Is milestone?
						false, // Is pointed ?
						(Brush)null // Explicit fill brush
					);
				} catch (IllegalArgumentException e1) {
				} catch (InstantiationException e1) {
				} catch (IllegalAccessException e1) {
				} catch (InvocationTargetException e1) {
				}

				// Draw the item with its default appearance through the ItemDrawContext
				if (context != null)
					context.drawDefault();

				p = p.withY(p.getY() + calendar.getItemSettings().getSize() + padding);
			}
		}
	}
	
	private void onCalendarSizeChanged(ComponentEvent e) {
		currentColumnBounds = null;
		infoColumn = -1;
		calendar.getSelection().setEnabled(true);
		calendar.getItemSettings().setShowMoreItemsCue(true);
	}

	private void onCalendarClicked(MouseEvent e) {
		if (ignoreNextClick)
		{
			ignoreNextClick = false;
			return;
		}

		if (currentColumnBounds != null)
		{
			// If the click is within the currently displayed info box, do
			// not close the info box; instead try to hit-test the items
			// in the info box
			Point point = calendar.pointToClient(new Point(e.getXOnScreen(), e.getYOnScreen()));
			if (currentColumnBounds.contains(point))
			{
				Point p = currentColumnBounds.getLocation();
				for (Item item : allItems)
				{
					int padding = 2;

					Rectangle itemBounds = new Rectangle(p.getX() + padding, p.getY() + padding,
						currentColumnBounds.getWidth() - 2 * padding, calendar.getItemSettings().getSize());

					if (itemBounds.contains(point))
					{
						// Note: Add custom logic here
						JOptionPane.showMessageDialog(this, item.getHeaderText() + " was clicked!");
					}

					p = new Point(p.getX(), p.getY() + calendar.getItemSettings().getSize() + padding);
				}

				return;
			}
		}

		currentColumnBounds = null;
		infoColumn = -1;
		calendar.getSelection().setEnabled(true);
		calendar.getItemSettings().setShowMoreItemsCue(true);

		calendar.invalidate();
	}

	private void onCalendarDraw(DrawEvent e) {
		if (e.getElement() == CustomDrawElements.TimetableTimelineHourCell)
		{
			Rectangle bounds = new Rectangle(e.getBounds());
			Brush brush = new SolidBrush(timelineFillColor);
			e.getGraphics().fillRectangle(brush, bounds);
			bounds = bounds.inflate(0, 0, -1, -1);
			Pen pen = new Pen(lineColor);
			e.getGraphics().drawRectangle(pen, bounds);
			e.getGraphics().drawLine(pen, e.getBounds().getLeft(), e.getBounds().getTop(),
					e.getBounds().getRight() - 1, e.getBounds().getTop());

			DateTime time = e.getDate().add(e.getStartTime());
			DateTime endTime = e.getDate().add(e.getEndTime());

			Brush textBrush = new SolidBrush(timelineTextColor);
			TextFormat format = new TextFormat(Align.Far, Align.Near);
			e.getGraphics().drawString(String.format("%1$s%2$s", time.toString("h ").trim(), time.getHour() >= 12 ? "pm" : "am"), 
					timelineFont, textBrush, new RectangleD(bounds), format);
			
			Duration firstVisibleTime = calendar.getFirstVisibleDate().getTimeOfDay();
			if (Duration.op_LessThanOrEqual(time.getTimeOfDay(), firstVisibleTime) && 
					Duration.op_LessThan(firstVisibleTime, endTime.getTimeOfDay()))
			{
				// Bottom line
				Pen bottomPen = new Pen(frameColor);
				e.getGraphics().drawLine(bottomPen, e.getBounds().getLeft(), e.getBounds().getTop() - 1, 
						e.getBounds().getRight() - 1, e.getBounds().getTop() - 1);
			}
		}
		else if (e.getElement() == CustomDrawElements.TimetableWholeDayHeader)
		{
			boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());
			boolean isTomorrow = DateTime.op_Equality(e.getDate().addDays(-1), DateTime.today());

			Pen pen = new Pen(lineColor);
			e.getGraphics().drawLine(pen, e.getBounds().getRight() - 1, e.getBounds().getTop(), 
					e.getBounds().getRight() - 1, e.getBounds().getBottom());

			if (isToday || isTomorrow)
			{
				// Fill
				if (isToday)
				{
					Brush brush = new SolidBrush(todayLightColor);
					e.getGraphics().fillRectangle(brush, e.getBounds());
				}

				// Left/right side
				Pen todayPen = new Pen(todayDarkColor);
				e.getGraphics().drawLine(todayPen, e.getBounds().getLeft() - 1, e.getBounds().getTop(), 
						e.getBounds().getLeft() - 1, e.getBounds().getBottom() - 1);
			}
		}
		else if (e.getElement() == CustomDrawElements.TimetableInfoHeader)
		{
			// Bottom line
			Pen bottomPen = new Pen(frameColor);
			e.getGraphics().drawLine(bottomPen, e.getBounds().getLeft(), e.getBounds().getBottom() - 1,
					e.getBounds().getRight() - 1, e.getBounds().getBottom() - 1);
		}
		else if (e.getElement() == CustomDrawElements.TimetableCell)
		{
			DateTime time = e.getDate().add(e.getStartTime());
			DateTime endTime = e.getDate().add(e.getEndTime());
			boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());
			boolean isTomorrow = DateTime.op_Equality(e.getDate().addDays(-1), DateTime.today());

			Pen pen = new Pen(lineColor);
			Pen todayPen = new Pen(todayDarkColor);

			if (time.getMinute() == 0)
				pen.setDashStyle(DashStyle.DotDot);
			else
				pen.setDashStyle(DashStyle.Solid);

			// Fill
			if (isToday)
			{
				Brush brush = new SolidBrush(todayLightColor);
				e.getGraphics().fillRectangle(brush, e.getBounds().getX() + 1, e.getBounds().getY() + 1,
						e.getBounds().getWidth() - 1, e.getBounds().getHeight() - 1);
			}

			// Bottom side
			e.getGraphics().drawLine(pen, e.getBounds().getLeft(), e.getBounds().getBottom() - 1,
					e.getBounds().getRight() - 1, e.getBounds().getBottom() - 1);

			if (time.getHour() == 0 && time.getMinute() == 0)
			{
				// Top side
				pen.setDashStyle(DashStyle.Solid);
				e.getGraphics().drawLine(pen, e.getBounds().getLeft(), e.getBounds().getTop(),
						e.getBounds().getRight() - 1, e.getBounds().getTop());
			}

			if (isToday || isTomorrow)
			{
				// Left side
				e.getGraphics().drawLine(todayPen, e.getBounds().getLeft(), e.getBounds().getTop(), 
						e.getBounds().getLeft(), e.getBounds().getBottom() - 1);
			}

			pen.dispose();
			todayPen.dispose();

			// Draw the current time indicator
			DateTime now = DateTime.now();
			if (DateTime.op_LessThanOrEqual(time, now) && DateTime.op_LessThan(now, endTime))
			{
				double relativePosition = ((double)now.getTicks() - time.getTicks()) / (endTime.getTicks() - time.getTicks());

				int position = e.getBounds().getTop() + (int)(e.getBounds().getHeight() * relativePosition);

				Pen nowPen = new Pen(nowColor, 2);
				
				e.getGraphics().drawLine(nowPen, e.getBounds().getLeft() + 3, position, e.getBounds().getRight() - 3, position);

				Point[] leftArrow = new Point[]
				{
					new Point(e.getBounds().getLeft() + 1, position - 4),
					new Point(e.getBounds().getLeft() + 1, position + 4),
					new Point(e.getBounds().getLeft() + 4 + 1, position),
				};
				Point[] rightArrow = new Point[]
				{
					new Point(e.getBounds().getRight() - 1, position - 4),
					new Point(e.getBounds().getRight() - 1, position + 4),
					new Point(e.getBounds().getRight() - 4 - 1, position),
				};

				Brush nowBrush = new SolidBrush(nowColor);
				e.getGraphics().fillPolygon(nowBrush, leftArrow);
				e.getGraphics().fillPolygon(nowBrush, rightArrow);
			}
		}
		else if (e.getElement() == CustomDrawElements.TimetableColumnHeader)
		{
			boolean isToday = DateTime.op_Equality(e.getDate(), DateTime.today());

			if (isToday)
			{
				// Fill
				Brush brush = new SolidBrush(todayDarkColor);
				e.getGraphics().fillRectangle(brush, e.getBounds());

				// Left side
				Pen pen = new Pen(todayDarkColor);
				e.getGraphics().drawLine(pen, e.getBounds().getLeft() - 1, e.getBounds().getTop(), 
						e.getBounds().getLeft() - 1, e.getBounds().getBottom() - 1);

				// Text
				Brush textBrush = new SolidBrush(e.getStyle().getHeaderTextColor());
				TextFormat format = new TextFormat(Align.Center, Align.Center);
				e.getGraphics().drawString(e.getText(), e.getStyle().getHeaderFont(), 
						textBrush, new RectangleD(e.getBounds()), format);
			}
		}
	}


	private static final long serialVersionUID = 1L;

	private Color lineColor = new Color(0xDD, 0xDD, 0xDD);
	private Color timelineTextColor = new Color(0x66, 0x66, 0x66);
	private Color timelineFillColor = new Color(0xF6, 0xF9, 0xFC);
	private Font timelineFont = new Font("Arial", 11, FontStyles.Regular);
	private Color headerTextColor = new Color(0x22, 0x00, 0xCC);
	private Font headerFont = new Font("Arial", 11, FontStyles.Regular);
	private Color headerFillColor = new Color(0xE3, 0xE9, 0xFF);
	private Color todayDarkColor = new Color(0xFA, 0xD1, 0x63);
	private Color todayLightColor = new Color(0xFF, 0xF7, 0xD7);
	private Color nowColor = new Color(0xFF, 0x7F, 0x6E);
	private Color frameColor = new Color(0xBB, 0xCC, 0xFF);
	
	private Rectangle currentColumnBounds;
	private ItemList allItems;

	private int infoColumn = -1;
	private boolean ignoreNextClick = false;
	
	class GoogleCalendar extends AwtCalendar
	{
		@Override
		protected void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);
			onCalendarPaintComponent(g);
		}
		
		private static final long serialVersionUID = 1L;
	}
}
