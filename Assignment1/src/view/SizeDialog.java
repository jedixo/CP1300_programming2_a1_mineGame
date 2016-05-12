package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SizeDialog extends JDialog {

	// SizeDialog variables
	private static final int MINROWS = 4;
	private static final int MAXROWS = 20; 
	private static final int INITIALROWS = 10;
	public int rows = 10;
	public int colums = 10;

	// SizeDialog objects
	JSlider rowsSlider;
	JSlider columsSlider;
	JButton okay;
	JLabel rowsLabel;
	JLabel columsLabel;

	public SizeDialog() {
		
		// sets up dialog items
		rowsSlider = new JSlider(JSlider.HORIZONTAL, MINROWS, MAXROWS, INITIALROWS);
		columsSlider = new JSlider(JSlider.HORIZONTAL, MINROWS, MAXROWS, INITIALROWS);
		okay = new JButton("okay");
		rowsLabel = new JLabel("rows: " + rows);
		columsLabel = new JLabel("columns: " + colums);

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 2));
		controlPanel.add(rowsLabel);
		controlPanel.add(rowsSlider);
		controlPanel.add(columsLabel);
		controlPanel.add(columsSlider);
		add(controlPanel, BorderLayout.CENTER);
		add(okay, BorderLayout.SOUTH);

		pack();
		setModal(true);
		setLocationRelativeTo(null);

		// sets new size and updates row label
		rowsSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				rows = (int) source.getValue();
				rowsLabel.setText("rows: " + rows);

			}
		});

		// sets new size and updates column label
		columsSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				colums = (int) source.getValue();
				columsLabel.setText("columns: " + colums);
			}
		});

		// ok button listener changes vibility of sizedialog
		okay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

}
