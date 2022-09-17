package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Calculator extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tf;
	JButton b[];
	String str[]={"%","CE","C","\u232b"," 1/x","x\u00B2","\u221A","/","7","8","9","*","4","5","6","-","1","2","3","+","+/-","0",".","="};
	JPanel p1;
	Font f1,f2;
	String equation;
	boolean concat=true;
	char op='\u0000';
	double old=0.0;
	Image ic;
	Calculator(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Calculator");
		ic=Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\logo.png");
		setIconImage(ic);
		f1=new Font("arial",Font.BOLD,70);
		f2=new Font("arial",Font.ITALIC,20);
		tf=new JTextField("0");
		tf.setBackground(new Color(26,26,26));
		//tf.setBorder(false);
		tf.setForeground(Color.WHITE);
		tf.setFont(f1);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		add(tf,BorderLayout.NORTH);
		b=new JButton[24];
		GridLayout grid=new GridLayout(6,4,2,2);
		p1=new JPanel(grid);
		for(int i=0;i<24;i++){
			b[i]=new JButton(str[i]);
			b[i].addActionListener(this);
			b[i].setForeground(Color.WHITE);
			if(str[i]=="="){
				b[i].setBackground(new Color(7,147,255));
			}
			else if(i>=0 && i<=7 || i==11 || i==15 || i==19){
				b[i].setBackground(new Color(50,50,50));
			}
			else{
				b[i].setBackground(new Color(59,59,59));
			}
			if(i!=3){
				b[i].setFont(f2);
			}
			p1.add(b[i]);
		}
		add(p1);
		setBounds(200,100,330,540);
		setVisible(true);
	}
	private void setIcon() {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent ae) {
		JButton b=(JButton)ae.getSource();
		String s=b.getText();
		String current=tf.getText();
		if(Character.isDigit(s.charAt(0))) {
			if(concat==true) {
				tf.setText(filter(current+s.charAt(0)));
			}
			else {
				tf.setText(filter(s));
			}
		}
		else if (isOperator(s.charAt(0))) {
			if(op!='\u0000') {
				double d=Double.parseDouble(current);
				if(op=='+') {
					old=old+d;
				}
				else if(op=='-') {
					old=old-d;
				}
				else if(op=='*') {
					old=old*d;
				}
				else if(op=='/') {
					old=old/d;
				}
				tf.setText(filter(old+""));
				current=tf.getText();
			}
			concat=false;
			op=s.charAt(0);
			old=Double.parseDouble(current);
		}
		else if(s.equals("=")) {
			double d=Double.parseDouble(current);
			if(op=='+') {
				tf.setText(filter(old+d+""));
			}
			else if(op=='-') {
				tf.setText(filter(old-d+""));
			}
			else if(op=='*') {
				tf.setText(filter(old*d+""));
			}
			else if(op=='/') {
				tf.setText(filter(old/d+""));
			}
		}
		else if(s.equals("C")) {
			tf.setText("0");
			old=0;
			op='\u0000';
		}
		else if(s.equals("x\u00B2")) {
			tf.setText(filter(Double.parseDouble(current)*Double.parseDouble(current)+""));
		}
		else if(s.equals(" 1/x")) {
			tf.setText(filter((1/Double.parseDouble(current))+""));
		}
		else if(s.equals("\u221A")) {
			tf.setText(filter(Math.sqrt(Double.parseDouble(current))+""));
		}
		else if(s.equals("CE")) {
			tf.setText("0");
		}
		else if(s.equals("\u232b")) {
			if(current.isEmpty()) {
				tf.setText("0");
			}
			tf.setText(current.substring(0,current.length()-1));
		}
		else if(s.equals(".")) {
			if(s.indexOf('.')==-1) {
				tf.setText(current+".");
			}
		}
		else if(s.equals("%")) {
			double d=old*Double.parseDouble(current)/100;
			tf.setText(filter(d+""));
		}
	}
	boolean isOperator(char value) {
		if(value=='+' || value=='-' || value=='*' || value=='/') {
			return true;
		}
		return false;
	}
	String filter(String value) {
		if(value.isEmpty()) {
			return "0";
		}
		double d=Double.parseDouble(value);
		if(d==(int)d) {
			return (int)d+"";
		}
		else {
			return d+"";
		}
	}
	public static void main(String[] args){
		new Calculator();
	}
}