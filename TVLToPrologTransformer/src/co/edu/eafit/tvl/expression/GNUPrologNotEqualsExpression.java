package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.NotEqualsExpression;

public class GNUPrologNotEqualsExpression implements GNUPrologExpression {

	private NotEqualsExpression expression;

	public GNUPrologNotEqualsExpression(NotEqualsExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #\\= " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
