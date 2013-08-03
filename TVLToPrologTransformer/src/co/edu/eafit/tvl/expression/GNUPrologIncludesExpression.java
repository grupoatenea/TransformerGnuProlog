package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.IncludesExpression;

public class GNUPrologIncludesExpression implements GNUPrologExpression {

	private IncludesExpression expression;

	public GNUPrologIncludesExpression(IncludesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		return expression.getLongID1() + " #==> " + expression.getLongID2();
	}

	
}
