package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.GEQExpression;

public class GNUPrologGreaterOrThanEqualsExpression implements GNUPrologExpression {

	private GEQExpression expression;

	public GNUPrologGreaterOrThanEqualsExpression(GEQExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologExpressionTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #>= " + GNUPrologExpressionTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
