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
	boolean point_flag1 = false;//变量1小数点存在标志
	boolean point_flag2 = false;//变量2小数点存在标志
	boolean operator_exist = false;//运算符存在标志，输入框中只能存在一个运算符
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
			//数字0--9和小数点
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
			//不能按“.”情况：1：输入框空时时；2：变量1已有小数点；3：变量2已有小数点
			if(str.length() == 0 || (!operator_exist && point_flag1) || (operator_exist && point_flag2)){
				
			}else{
				et_input.setText(str+((Button)v).getText());
				if(!operator_exist){
					point_flag1 = true;//变量1数点存在
				}else{
					point_flag2 = true;
				}
			}		
			break;
			
			//加减乘除
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
			
			//后退一个
		case R.id.btn_delete:
			if(str!=null&&!str.equals("")){	
//				System.out.println("此时："+et_input.getText().toString());
//				System.out.println("最后一个字符："+et_input.getText().toString().charAt(et_input.getText().toString().length()-1));
				//如果回退时，输入框最后尾部字符为“ ”，表示结尾为一个运算符，此时需要回退3个字符
				if(Character.isWhitespace(str.charAt(str.length()-1))){
//					System.out.println("回退三格");
					operator_exist = false;
					point_flag2 = false;
					et_input.setText(str.substring(0,str.length()-3));//？？？未成功不知道为什么
				}else{
					et_input.setText(str.substring(0,str.length()-1));
				}		
				
			}
			break;
			
			//清除输入框
		case R.id.btn_clear:
			operator_exist = false;
			point_flag1 = false;
			point_flag2 = false;
			str="";
			et_input.setText("");
			break;
			
			//计算
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

	//运算结果
	private void getResult(){
		String exp=et_input.getText().toString();//获得输入框字符串
		if(exp==null||exp.equals("")){
			return;
		}
		if(!exp.contains(" ")){
			return;
		}

		double result=0;
		String s1=exp.substring(0,exp.indexOf(" "));//运算符前面的字符
		String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//运算符
		String s2=exp.substring(exp.indexOf(" ")+3);//运算符后面的字符串
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
