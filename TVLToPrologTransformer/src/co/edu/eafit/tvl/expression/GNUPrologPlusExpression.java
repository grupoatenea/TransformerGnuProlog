package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.PlusExpression;

public class GNUPrologPlusExpression implements GNUPrologExpression {
	
	private PlusExpression plusExpression;

	public GNUPrologPlusExpression(PlusExpression plusExpression){
		this.plusExpression = plusExpression;
	}

	@Override
	public String toArithmeticForm() {
		String sum1 = GNUPrologExpressionTransformer.transform(plusExpression.getExpression1()).toArithmeticForm();
		String sum2 = GNUPrologExpressionTransformer.transform(plusExpression.getExpression2()).toArithmeticForm();
		return sum1 + " + " + sum2;
	}

}
