package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.LowerExpression;

public class GNUPrologLowerExpression implements GNUPrologExpression {

	private LowerExpression expression;

	public GNUPrologLowerExpression(LowerExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #< " + GNUPrologExpressionTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
