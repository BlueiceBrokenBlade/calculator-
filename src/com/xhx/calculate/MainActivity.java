package com.xhx.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener{
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;	
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;
	Button btn_point;
	Button btn_clear;
	Button btn_delete;
	Button btn_plus;
	Button btn_minus;	
	Button btn_multiply;
	Button btn_divide;
	Button btn_equle;	
	EditText et_input;
	boolean point_flag1 = false;//����1С������ڱ�־
	boolean point_flag2 = false;//����2С������ڱ�־
	boolean operator_exist = false;//��������ڱ�־���������ֻ�ܴ���һ�������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_0=(Button) findViewById(R.id.btn_0);
		btn_1=(Button) findViewById(R.id.btn_1);
		btn_2=(Button) findViewById(R.id.btn_2);
		btn_3=(Button) findViewById(R.id.btn_3);
		btn_4=(Button) findViewById(R.id.btn_4);
		btn_5=(Button) findViewById(R.id.btn_5);
		btn_6=(Button) findViewById(R.id.btn_6);
		btn_7=(Button) findViewById(R.id.btn_7);
		btn_8=(Button) findViewById(R.id.btn_8);
		btn_9=(Button) findViewById(R.id.btn_9);
		btn_point=(Button) findViewById(R.id.btn_point);
		btn_clear=(Button) findViewById(R.id.btn_clear);
		btn_delete=(Button) findViewById(R.id.btn_delete);
		btn_plus=(Button) findViewById(R.id.btn_plus);
		btn_minus=(Button) findViewById(R.id.btn_minus);	
		btn_multiply=(Button) findViewById(R.id.btn_multiply);
		btn_divide=(Button) findViewById(R.id.btn_divide);
		btn_equle=(Button) findViewById(R.id.btn_equal);
		et_input=(EditText) findViewById(R.id.et_input);
		
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);		
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);		
		btn_clear.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);		
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equle.setOnClickListener(this);			
	}

	@Override
	public void onClick(View v) {
		String str=et_input.getText().toString();
		switch(v.getId()){
			//����0--9��С����
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
			if(str.equals("0")){
				et_input.setText(((Button)v).getText());
			}else{
				et_input.setText(str+((Button)v).getText());
			}
			break;
		case R.id.btn_point:
			//���ܰ���.�������1��������ʱʱ��2������1����С���㣻3������2����С����
			if(str.length() == 0 || (!operator_exist && point_flag1) || (operator_exist && point_flag2)){
				
			}else{
				et_input.setText(str+((Button)v).getText());
				if(!operator_exist){
					point_flag1 = true;//����1�������
				}else{
					point_flag2 = true;
				}
			}		
			break;
			
			//�Ӽ��˳�
		case R.id.btn_plus:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if(operator_exist || '.'==str.charAt(str.length()-1) ){
				break;
			}
			operator_exist = true;
			
			et_input.setText(str+" "+((Button)v).getText()+" ");
			break;
			
			//����һ��
		case R.id.btn_delete:
			if(str!=null&&!str.equals("")){	
//				System.out.println("��ʱ��"+et_input.getText().toString());
//				System.out.println("���һ���ַ���"+et_input.getText().toString().charAt(et_input.getText().toString().length()-1));
				//�������ʱ����������β���ַ�Ϊ�� ������ʾ��βΪһ�����������ʱ��Ҫ����3���ַ�
				if(Character.isWhitespace(str.charAt(str.length()-1))){
//					System.out.println("��������");
					operator_exist = false;
					point_flag2 = false;
					et_input.setText(str.substring(0,str.length()-3));//������δ�ɹ���֪��Ϊʲô
				}else{
					et_input.setText(str.substring(0,str.length()-1));
				}		
				
			}
			break;
			
			//��������
		case R.id.btn_clear:
			operator_exist = false;
			point_flag1 = false;
			point_flag2 = false;
			str="";
			et_input.setText("");
			break;
			
			//����
		case R.id.btn_equal:
			operator_exist = false;
			if(point_flag1 || point_flag2){
				point_flag1 = true;
				point_flag2 = false;
			}		
			getResult();
			break;
		}
	}

	//������
	private void getResult(){
		String exp=et_input.getText().toString();//���������ַ���
		if(exp==null||exp.equals("")){
			return;
		}
		if(!exp.contains(" ")){
			return;
		}

		double result=0;
		String s1=exp.substring(0,exp.indexOf(" "));//�����ǰ����ַ�
		String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//�����
		String s2=exp.substring(exp.indexOf(" ")+3);//�����������ַ���
		if(!s1.equals("")&&!s2.equals("")){
			double d1=Double.parseDouble(s1);
			double d2=Double.parseDouble(s2);
			if(op.equals("+")){
				result=d1+d2;
			}else if(op.equals("-")){
				result=d1-d2;
			}else if(op.equals("*")){
				result=d1*d2;
			}else if(op.equals("/")){
				if(d2==0){
					result=0;
				}else{
					result=d1/d2;
				}
			}
			if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")){
				int r=(int)result;
				et_input.setText(r+"");
			}else{
				et_input.setText(result+"");
			}
		}else if(!s1.equals("")&&s2.equals("")){
			et_input.setText(exp);
		}else if(s1.equals("")&&!s2.equals("")){
			double d2=Double.parseDouble(s2);
			if(op.equals("+")){
				result=0+d2;
			}else if(op.equals("-")){
				result=0-d2;
			}else if(op.equals("*")){
				result=0;
			}else if(op.equals("/")){
				result=0;
			}
			if(!s2.contains(".")){
				int r=(int)result;
				et_input.setText(r+"");
			}else{
				et_input.setText(result+"");
			}
		}else{
			et_input.setText("");
		}
	}
}