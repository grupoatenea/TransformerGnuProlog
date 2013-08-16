package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.RealExpression;

public class GNUPrologRealExpression implements GNUPrologExpression {
	
	private RealExpression realExpression;

	public GNUPrologRealExpression(RealExpression realExpression){
		this.realExpression = realExpression;
	}

	@Override
	public String toArithmeticForm() {
		throw new IllegalArgumentException("Real Expression is not supported. Value: " + realExpression.getValue());
	}
	

}
