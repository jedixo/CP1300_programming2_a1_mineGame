package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {

	// MainFrame's objects and variables
	public static JMenuBar menubar = new JMenuBar();

	public MainFrame(GameDisplay panel) {

		// sets up MainFrame items
		JMenuBar menubar = setupMenu();

		// sets up main frame game display is passed into MainFrame
		add(panel, BorderLayout.CENTER);
		setJMenuBar(menubar);
		
		setPreferredSize(new Dimension(860, 700));
		setVisible(true);
		pack();
		setTitle("MineSweeper Java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	// sets up the menu bar
	private static JMenuBar setupMenu() {

		// creates menu and its respective items
		JMenu settingsMenu = new JMenu("Settings");
		JMenuItem size = new JMenuItem("Size");
		JMenuItem reset = new JMenuItem("New Game");

		JMenu dificultyMenu = new JMenu("Difficulty");
		ButtonGroup levelGroup = new ButtonGroup();
		JCheckBoxMenuItem easyDificulty = new JCheckBoxMenuItem("Easy");
		JCheckBoxMenuItem mediumDificulty = new JCheckBoxMenuItem("Medium");
		JCheckBoxMenuItem hardDificulty = new JCheckBoxMenuItem("Hard");

		easyDificulty.setSelected(true);

		// meaks sure only one box is checked
		levelGroup.add(easyDificulty);
		levelGroup.add(mediumDificulty);
		levelGroup.add(hardDificulty);

		dificultyMenu.add(easyDificulty);
		dificultyMenu.add(mediumDificulty);
		dificultyMenu.add(hardDificulty);

		settingsMenu.add(size);
		settingsMenu.add(dificultyMenu);
		settingsMenu.add(reset);
		menubar.add(settingsMenu);
		
		reset.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		size.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		easyDificulty.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
		mediumDificulty.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
		hardDificulty.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));


		return menubar;
	}

	// recursive
	private static List<JMenuItem> getMenuItems(JMenuItem item) {
		List<JMenuItem> items = new ArrayList<>();

		if (item instanceof JMenu) {
			JMenu menu = (JMenu) item;
			for (int i = 0; i < menu.getItemCount(); ++i) {
				items.addAll(getMenuItems(menu.getItem(i)));
			}
		} else {
			items.add(item);
		}

		return items;
	}

	// menu handler allows App to control the buttons
	public void addMenuHandler(ActionListener listener) {
		for (int i = 0; i < menubar.getMenuCount(); ++i) {
			for (JMenuItem item : getMenuItems(menubar.getMenu(i))) {
				item.addActionListener(listener);
			}
		}

	}

	// sets game state to false, shows game over dialog and reveals all cells
	public static void gameOver() {
		JOptionPane.showMessageDialog(null, "Game Over");
		
		// return false;
	}

	// displays not playing dialog
	public static void notPlaying() {
		JOptionPane.showMessageDialog(null, "Not Playing");
	}

	// displays win dialog and sets game state to false
	public static void Win() {
		JOptionPane.showMessageDialog(null, "You Completed the Game!");
	}
}
