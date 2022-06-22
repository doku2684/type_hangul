package type_hangul;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 

public class Type_Hangul extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel screen = new JPanel(new FlowLayout());
	private JPanel keyboard = new JPanel(new GridLayout(4,3));
	private JPanel clickPad = new JPanel(new FlowLayout());
	private String message = " ";
	private static int currentKey = 0;
	private static JButton[] buttons = new JButton[12];
	private static long clickTime = 0;
	
	public Type_Hangul(int waitTimeMillis) {
    	
    	String[] alphabets = {"ㅣ", ".", "ㅡ", "ㄱㅋ", "ㄴㄹ", "ㄷㅌ", "ㅂㅍ", "ㅅㅎ", "ㅈㅊ", "지우기", "ㅇㅁ", "띄어쓰기"};
    	
        setSize(300, 500); //크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("한글 작성 도우미");
        
        setLayout(new BorderLayout()); //배치 관리자 설정
        this.add(screen, BorderLayout.NORTH);
        this.add(keyboard, BorderLayout.CENTER);
        this.add(clickPad, BorderLayout.SOUTH);
        
        for (int i = 0; i < 12; i++) {
        	buttons[i] = new JButton(alphabets[i]);
        	buttons[i].setOpaque(true);
        	buttons[i].setBackground(Color.white);
        	keyboard.add(buttons[i]);
        }
        JLabel messageBox = new JLabel(message);
        screen.add(messageBox);
        JButton clickButton = new JButton();
        clickButton.setPreferredSize(new Dimension(this.getWidth(),200));
        clickPad.add(clickButton);
        
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	message = message + currentKey;
            	messageBox.setText(message);
            }
        });
        
        clickButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent m) {
        		clickTime = System.currentTimeMillis();
        	}
        	
        	@Override
        	public void mouseReleased(MouseEvent m) {
        		long releaseTime = System.currentTimeMillis();
        		if (releaseTime-clickTime > waitTimeMillis) {
        			message = " ";
        			messageBox.setText(message);
        		}
        	}
        });

        setVisible(true);
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int waitTimeMillis = Integer.parseInt(args[0]);
		new Type_Hangul(waitTimeMillis);
		while(true) {
			buttons[currentKey].setBackground(Color.blue);
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buttons[currentKey].setBackground(Color.white);
			currentKey = (currentKey+1)%12;
			
		}	
	}

}
