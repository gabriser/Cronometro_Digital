package cronometro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Cursor;

public class Cronometro {

	private JFrame frame;
	private javax.swing.Timer timer;
	private int milis;
	private boolean isBasic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cronometro window = new Cronometro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cronometro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		isBasic = true;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 51, 51));
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setForeground(new Color(204, 204, 204));
		frame.setBackground(new Color(204, 204, 204));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Cronometro.class.getResource("/font/cronometro.png")));
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cronometro Digital");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(170, 170, 170));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(82, 165, 252));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblBasicMode = new JLabel("Basic Mode");
		lblBasicMode.setBackground(new Color(82, 165, 252));
		lblBasicMode.setFont(new Font("Lato", Font.BOLD, 15));
		panel_1.add(lblBasicMode);
		lblBasicMode.setForeground(Color.WHITE);
		
		// Boton de iniciar el cronometro
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Lato", Font.BOLD, 15));
		btnStart.setForeground(Color.WHITE);
		btnStart.setBackground(new Color(82, 165, 252));
		panel.add(btnStart);
		
		// Boton de parar el cronometro
		JButton btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Lato", Font.BOLD, 15));
		btnStop.setForeground(Color.WHITE);
		btnStop.setBackground(new Color(82, 165, 252));
		btnStop.setVisible(false);
		panel.add(btnStop);
		
		// Boton de reiniciar el cronometro
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Lato", Font.BOLD, 15));
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(new Color(82, 165, 252));
		panel.add(btnReset);
		
		// Boton para cambiar al modo Basico
		JButton btnChangeBasic = new JButton("Change Basic");
		btnChangeBasic.setForeground(Color.WHITE);
		btnChangeBasic.setBackground(new Color(191, 63, 63));
		btnChangeBasic.setFont(new Font("Lato", Font.BOLD, 15));
		btnChangeBasic.setVisible(false);
		panel.add(btnChangeBasic);
		
		// Boton para cambiar al modo Avanzado
		JButton btnChangeAdvanced = new JButton("Change Advanced");
		btnChangeAdvanced.setForeground(Color.WHITE);
		btnChangeAdvanced.setFont(new Font("Lato", Font.BOLD, 15));
		btnChangeAdvanced.setBackground(new Color(191, 63, 63));
		panel.add(btnChangeAdvanced);
		
		// Cronometro por defecto
		JLabel label = new JLabel("00:00");
		frame.getContentPane().add(label, BorderLayout.CENTER);
		label.setBackground(new Color(51, 51, 51));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(83, 165, 252));
		
		// Fuente personalizada para el cronometro
		try {
			Font digital = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/digital-7.ttf"));
			digital = digital.deriveFont(Font.PLAIN, 50);
			label.setFont(digital);
			
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
			label.setFont(new Font("FreeSans", Font.PLAIN, 50));
		}
		
		// Escuchar el boton de inicio
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnStart.setVisible(false);
				btnStop.setVisible(true);
				btnReset.setVisible(false);
				btnChangeBasic.setVisible(false);
				btnChangeAdvanced.setVisible(false);
				
				// Funcionalidad del cronometro con Swing Timer
				ActionListener  taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	milis++;
		            	int micro=milis%1000;
						int seg = (milis-milis%1000)/1000;
						int min = (seg-seg%60)/60;
						while (!(seg < 60)) {
							seg = seg-60;
						}
						//System.out.println(String.format("%02d", min)+":"+String.format("%02d", seg)+":"+String.format("%03d", micro));
						if (isBasic == true) {
							label.setText(String.format("%02d", min)+":"+String.format("%02d", seg));
						}
						else {
							label.setText(String.format("%02d", min)+":"+String.format("%02d", seg)+":"+String.format("%03d", micro));
						}
		            }
		        };
		        timer = new javax.swing.Timer(1 ,taskPerformer);
		        timer.start();
		        
		     // Escuchar el boton de parar
		        btnStop.addMouseListener(new MouseAdapter() {
		        	public void mouseClicked(MouseEvent e) {
		        		btnStart.setVisible(true);
						btnStop.setVisible(false);
						btnReset.setVisible(true);
						btnChangeBasic.setVisible(false);
						btnChangeAdvanced.setVisible(false);
		        		timer.stop();
		        		
		        	}
		        });
		
			}
		});
		
		// Escuchar el boton de reinicio
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				milis=0;
				
				btnStart.setVisible(true);
				btnStop.setVisible(false);
				btnReset.setVisible(true);
				if (isBasic == true) {
					btnChangeBasic.setVisible(false);
					btnChangeAdvanced.setVisible(true);
					
					label.setText("00:00");
				} else {
					btnChangeBasic.setVisible(true);
					btnChangeAdvanced.setVisible(false);
					
					label.setText("00:00:000");
				}
				
			}
		});
		
		// Escuchar el boton de Cambiar al Modo Basico
		btnChangeBasic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnChangeBasic.setVisible(false);
				btnChangeAdvanced.setVisible(true);
				
				label.setText("00:00");
				lblBasicMode.setText("Basic Mode");
				panel_1.setBackground(new Color(82, 165, 252));
				
				isBasic = true;
			}
		});
		
		// Escuchar el boton de Cambiar al Modo Avanzado
		btnChangeAdvanced.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnChangeBasic.setVisible(true);
				btnChangeAdvanced.setVisible(false);
				
				label.setText("00:00:000");
				lblBasicMode.setText("Advanced Mode");
				panel_1.setBackground(new Color(191, 63, 63));
				
				isBasic = false;
			}
		});
	}

}
