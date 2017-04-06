package com.riotgames.sample;

import java.util.*;


/**
 * Calculator application
 */
public class CalcApp {
	public double calc(String[] tokens) {
    	Deque<String> s = new LinkedList();
    	for(int i = 0 ; i < tokens.length ; i++){
    		s.add(tokens[i]);
    	}
    	
    	return gualho(s);

    }
    
    public double gualho(Deque<String> s){
    	
    	Deque<String> tempq = new LinkedList();
    	
    	for(;!(s.isEmpty());){
    		
    		String polls = s.poll();
    		
    		if("(".equals(polls)){
    			tempq.add(Double.toString(gualho(s)));
    		}
    		else if(")".equals(polls)){
    			
    			return doudiv(tempq);
    		}
    		else{
    			if("-".equals(polls)){
    				tempq.add("+");
    				tempq.add(Double.toString((-Double.parseDouble(s.poll()))));
    			}
    			else{
    				tempq.add(polls);
    			}
    		}
    	}
    	
    	return doudiv(tempq);
    }
    
    public double doudiv(Deque<String> s){
    	
    	Deque<String> tempq = new LinkedList();
    	
    	for(;!(s.isEmpty());){
    		
    		String polls = s.poll();
    		
    		if("x".equals(polls) || "/".equals(polls)){
    			double firstOp;
    			double secondOp;
    			
    			firstOp = Double.parseDouble(tempq.pollLast());
    			secondOp = Double.parseDouble(s.poll());
    			Operator Op = Operator.findOperator(polls);
    			
    			tempq.add(Double.toString(Op.evaluate(firstOp,secondOp)));
    		}
    		else{
    			tempq.add(polls);
    		}
    	}
    	for(;!(tempq.size()==1);){
    		double firstOp;
			double secondOp;
			
			secondOp = Double.parseDouble(tempq.pop());
			Operator Op = Operator.findOperator(tempq.pop());
			firstOp = Double.parseDouble(tempq.pop());
			
			tempq.push((Double.toString(Op.evaluate(firstOp,secondOp))));
    	}
    	return Double.parseDouble(tempq.pop());
    }


    public static void main( String[] args ) {
        final CalcApp app = new CalcApp();
        final StringBuilder outputs = new StringBuilder();
        Arrays.asList(args).forEach(value -> outputs.append(value + " "));
        System.out.print( "Addition of values: " + outputs + " = ");
        System.out.println(app.calc(args));
    }
}
