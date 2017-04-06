package com.riotgames.sample;

import java.util.*;


/**
 * Calculator application
 */
public class CalcApp {
	public double calc(String[] tokens) {
    	Queue<String> s = new LinkedList();
    	for(int i = 0 ; i < tokens.length ; i++){
    		s.add(tokens[i]);
    	}
    	
    	return gualho(s);

    }
    
public double gualho(Queue<String> s){
    	
    	Queue<String> tempq = new LinkedList();
    	
    	for(;!(s.isEmpty());){
    		
    		String polls = s.poll();
    		
    		if(")".equals(polls)){
    			tempq.add(""+ gualho(s) +"");
    		}
    		else if("(".equals(polls)){
    			return doudiv(tempq);
    		}
    		else{
    			tempq.add(polls);
    		}
    	}
    	
    	return doudiv(tempq);
    }
    
    public double doudiv(Queue<String> s){
    	
    	Queue<String> tempq = new LinkedList();
    	
    	for(;!(s.isEmpty());){
    		
    		String polls = s.poll();
    		
    		if("*".equals(polls)){
    			double firstOp;
    			double secondOp;
    			
    			firstOp = Double.parseDouble(tempq.poll());
    			secondOp = Double.parseDouble(s.poll());
    			Operator Op = Operator.findOperator(polls);
    			
    			tempq.add(""+Op.evaluate(firstOp,secondOp)+"");
    		}
    		else if("/".equals(polls)){
    			double firstOp;
    			double secondOp;
    			
    			firstOp = Double.parseDouble(tempq.poll());
    			secondOp = Double.parseDouble(s.poll());
    			Operator Op = Operator.findOperator(polls);
    			
    			tempq.add(""+Op.evaluate(firstOp,secondOp)+"");
    		}
    		else{
    			tempq.add(polls);
    		}
    	}
    	for(;!(tempq.size()==1);){
    		double firstOp;
			double secondOp;
			
			secondOp = Double.parseDouble(tempq.poll());
			Operator Op = Operator.findOperator(tempq.poll());
			firstOp = Double.parseDouble(tempq.poll());
			
			tempq.add(""+Op.evaluate(secondOp,firstOp)+"");
    	}
    	return Double.parseDouble(tempq.poll());
    }


    public static void main( String[] args ) {
        final CalcApp app = new CalcApp();
        final StringBuilder outputs = new StringBuilder();
        Arrays.asList(args).forEach(value -> outputs.append(value + " "));
        System.out.print( "Addition of values: " + outputs + " = ");
        System.out.println(app.calc(args));
    }
}
