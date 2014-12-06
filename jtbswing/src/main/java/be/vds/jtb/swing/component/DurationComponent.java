package be.vds.jtb.swing.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import be.vds.jtb.swing.layout.GridBagLayoutManager;

public class DurationComponent extends JPanel {

	public static final String TIME_PROPERTY = "time_changed";
	private long maximumValue = -1;
	private long minimumValue = -1;
	private static final String SEPARATOR = ":";
	private static final String FORMAT = "HH" + SEPARATOR + "mm" + SEPARATOR
			+ "ss";
	private static final String PATTERN_HRS_MIN_SEC = "(\\d*)[:](\\d*)[:](\\d*)";

	private int INCREMENT_TIMER_MAX = 800;
	private int INCREMENT_TIMER_MIN = 40;
	private long INCREMENT = 1;
	private long INCREMENT_DEFAULT = 1000;

	/**
	 * in miliseconds
	 */
	private long time;
	private JFormattedTextField durationTf;

	public DurationComponent() {
		setMinimumValue(0);
		init();
	}

	public void setMinimumValue(int minimumValue) {
		this.minimumValue = minimumValue;
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Insets i1 = new Insets(0, 0, 0, 0);

		gc.insets = i1;
		GridBagLayoutManager.addComponent(this, createLabelComponent(), gc, 0,
				0, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL,
				GridBagConstraints.CENTER);
		GridBagLayoutManager.addComponent(this, createButtons(), gc, 4, 0, 1,
				1, 1, 0, GridBagConstraints.HORIZONTAL,
				GridBagConstraints.CENTER);
		adaptTf();
	}

	private Component createLabelComponent() {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		durationTf = new JFormattedTextField(format);
		durationTf.setEditable(true);
		durationTf.setHorizontalAlignment(JTextField.RIGHT);
		durationTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					Pattern p = Pattern.compile(PATTERN_HRS_MIN_SEC);
					Matcher m = p.matcher(durationTf.getText());
					if (m.matches()) {
						setTime(m.group(1), m.group(2), m.group(3), "0");
					} else {
						setTime(time);
					}
				}
			}
		});
		return durationTf;
	}

	private void setTime(String hours, String minutes, String seconds,
			String miliseconds) {
		setTime(Long.valueOf(hours), Long.valueOf(minutes),
				Long.valueOf(seconds), Long.valueOf(miliseconds));
	}

	private void setTime(long hours, long minutes, long seconds,
			long miliseconds) {
		long time = miliseconds;
		time += seconds * getSecondsToModel();
		time += minutes * getMinutesToModel();
		time += hours * getHoursToModel();
		setTime(time);
	}

	public void setTime(long time) {
		if ((minimumValue == -1 || time >= minimumValue)
				&& (maximumValue == -1 || time <= maximumValue)) {
			long oldTime = this.time;
			this.time = time;
			adaptTf();
			firePropertyChange(TIME_PROPERTY, oldTime, this.time);
		}
	}

	private Component createButtons() {
		final Timer timerPlus = new Timer(INCREMENT_TIMER_MAX,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						increment(+1);
					}
				});

		final Timer timerMinus = new Timer(INCREMENT_TIMER_MAX,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						increment(-1);
					}
				});

		JButton plusBtn = new JButton(new ImageIcon(getClass().getClassLoader()
				.getResource("triangle_up_16.png")));
		plusBtn.setFocusable(false);
		plusBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				timerPlus.setDelay(INCREMENT_TIMER_MAX);
				increment(+1);
				timerPlus.start();
				timerPlus.setDelay(INCREMENT_TIMER_MIN);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				timerPlus.stop();
				timerPlus.setDelay(INCREMENT_TIMER_MAX);
			}
		});

		JButton minusBtn = new JButton(new ImageIcon(getClass().getClassLoader()
				.getResource("triangle_down_16.png")));
		minusBtn.setFocusable(false);
		minusBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				timerMinus.setDelay(INCREMENT_TIMER_MAX);
				increment(-1);
				timerMinus.start();
				timerMinus.setDelay(INCREMENT_TIMER_MIN);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				timerMinus.stop();
				timerMinus.setDelay(INCREMENT_TIMER_MAX);
			}
		});

		Dimension d = new Dimension(15, (int) durationTf.getPreferredSize()
				.getHeight() / 2);
		plusBtn.setPreferredSize(d);
		minusBtn.setPreferredSize(d);

		JPanel p = new JPanel(new BorderLayout(0, 0));
		p.setOpaque(false);
		p.add(plusBtn, BorderLayout.NORTH);
		p.add(minusBtn, BorderLayout.SOUTH);
		return p;
	}

	private void increment(int incr) {
		int start = durationTf.getSelectionStart();
		int end = durationTf.getSelectionEnd();

		if (start == end) {
			long newTime = time + INCREMENT_DEFAULT * incr;
			setTime(newTime);
		} else {
			int sepLen = SEPARATOR.length();
			int strLen = durationTf.getText().length();

			if (start == strLen - 2 && end == strLen) {
				long newTime = time + (INCREMENT * getSecondsToModel() * incr);
				setTime(newTime);
			} else if (start == strLen - 4 - sepLen
					&& end == strLen - 2 - sepLen) {
				long newTime = time + (INCREMENT * incr * getMinutesToModel());
				setTime(newTime);
			} else if (start == 0 && end == strLen - 4 - (2 * sepLen)) {
				long newTime = time + (INCREMENT * incr * getHoursToModel());
				setTime(newTime);
			}
			selectText(start, end);
		}
	}

	private long getHoursToModel() {
		return 3600000;
	}

	private long getMinutesToModel() {
		return 60000;
	}

	private long getSecondsToModel() {
		return 1000;
	}

	private void selectText(int start, int end) {
		durationTf.setSelectionStart(start);
		durationTf.setSelectionEnd(end);
	}

	private void adaptTf() {
		long hrs = (long) (time / getHoursToModel());
		long min = (long) ((time - hrs * getHoursToModel()) / getMinutesToModel());
		long sec = (long) (time - (hrs * getHoursToModel()) - (min * getMinutesToModel()))
				/ getSecondsToModel();

		String s = formatInMinTwoDigits(hrs) + SEPARATOR
				+ formatInMinTwoDigits(min) + SEPARATOR
				+ formatInMinTwoDigits(sec);
		durationTf.setText(s);
	}

	private String formatInMinTwoDigits(long digit) {
		String s = String.valueOf(digit);
		if (s.length() < 2) {
			return "0" + s;
		}
		return s;
	}

	public long getTime() {
		return time;
	}

	public long getMinimumValue() {
		return minimumValue;
	}

	public void setMaximumValue(long maximumValue) {
			this.maximumValue = maximumValue;		
	}
}
