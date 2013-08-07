package co.edu.eafit.tvl.expression;

import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExcludesExpression;

public class GNUPrologExcludesExpression implements GNUPrologExpression {

	private ExcludesExpression expression;

	public GNUPrologExcludesExpression(ExcludesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		String name1 = GNUPrologNamesContainer.getInstance().getName(expression.getLongID1());
		String name2 = GNUPrologNamesContainer.getInstance().getName(expression.getLongID2());
		return name1 + " + " + name2 + " #=< 1";
	}
}
