package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.IntExpression;

public class GNUPrologIntExpression implements GNUPrologExpression {

	private IntExpression intExpression;
	
	public GNUPrologIntExpression(IntExpression expression) {
		this.intExpression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return String.valueOf(intExpression.getValue());
	}
	
}
