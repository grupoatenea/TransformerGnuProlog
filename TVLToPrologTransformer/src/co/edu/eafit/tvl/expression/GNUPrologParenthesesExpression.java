package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.ParenthesesExpression;

public class GNUPrologParenthesesExpression implements GNUPrologExpression {

	private ParenthesesExpression expression;

	public GNUPrologParenthesesExpression(ParenthesesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return "( " + GNUPrologTransformer.transform(expression.getExpression()).toArithmeticForm() + " )";
	}

}
