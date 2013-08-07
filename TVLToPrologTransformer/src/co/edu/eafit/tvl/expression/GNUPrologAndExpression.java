package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.AndExpression;

public class GNUPrologAndExpression implements GNUPrologExpression {

	private AndExpression expression;

	public GNUPrologAndExpression(AndExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #/\\ " + GNUPrologExpressionTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
