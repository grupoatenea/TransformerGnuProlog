package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.EqualsExpression;

public class GNUPrologEqualsExpression implements GNUPrologExpression {

	private EqualsExpression expression;

	public GNUPrologEqualsExpression(EqualsExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return GNUPrologTransformer.transform( expression.getExpression1() ).toArithmeticForm() + " #= " + GNUPrologTransformer.transform( expression.getExpression2() ).toArithmeticForm();
	}
	
	

}
