package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.ExcludesExpression;

public class GNUPrologExcludesExpression implements GNUPrologExpression {

	private ExcludesExpression expression;

	public GNUPrologExcludesExpression(ExcludesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return expression.getLongID1() + " + " + expression.getLongID2() + " #=< 1";
	}
}
